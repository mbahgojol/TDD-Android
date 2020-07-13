package com.blank.chapter10.ui.login

import androidx.lifecycle.Observer
import com.blank.chapter10.data.AppDataManager
import com.blank.chapter10.data.model.BodyLogin
import com.blank.chapter10.data.model.Data
import com.blank.chapter10.data.model.ResponseLogin
import com.blank.chapter10.utils.api.BaseErrorDataSourceApi
import com.blank.chapter10.utils.api.ResultState
import com.edwinnrw.moviecleanarchitecture.helper.InstantRuleExecution
import com.edwinnrw.moviecleanarchitecture.helper.TrampolineSchedulerRX
import com.google.gson.Gson
import com.jraska.livedata.test
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import retrofit2.HttpException
import retrofit2.Response

@RunWith(JUnitPlatform::class)
class LoginViewModelTest : Spek({
    Feature("Login") {
        val appDataManager = mock<AppDataManager>()
        val viewModel = LoginViewModel(appDataManager)
        val observerLogin = mock<Observer<ResultState>>()

        val bodyLogin = BodyLogin(
            email = "ghozimahdi@gmail.com",
            password = "123123"
        )

        beforeFeature {
            TrampolineSchedulerRX.start()
            InstantRuleExecution.start()
            viewModel.stateResponseLogin.observeForever(observerLogin)
        }

        Scenario("do on login, response success") {
            val expectedResult =
                ResponseLogin(Data("1", "ghozimahdi@gmail.com", "ghozimahdi", "token"), true)
            var expectedResultState: ResultState

            Given("set succes expected result state") {
                expectedResultState = ResultState.Success(expectedResult, "")

                given(appDataManager.login(bodyLogin)).willReturn(Single.just(expectedResultState))
            }

            When("request api login") {
                viewModel.login(bodyLogin)
            }

            Then("result success and return data") {
                verify(observerLogin, atLeastOnce()).onChanged(ResultState.Loading)
                verify(observerLogin, atLeastOnce()).onChanged(
                    ResultState.Success(
                        expectedResult,
                        ""
                    )
                )
            }
        }

        Scenario("do on login, response error") {
            val baseErrorDataSourceApi = BaseErrorDataSourceApi(false, "error")
            val responseBody: Response<BaseErrorDataSourceApi> = Response.error(
                422,
                Gson().toJson(baseErrorDataSourceApi).toString().toResponseBody()
            )
            val errorExpected = HttpException(responseBody)

            Given("set error expected result state") {
                Mockito.`when`(appDataManager.login(bodyLogin))
                    .thenReturn(Single.error(errorExpected))
            }

            When("request api login") {
                viewModel.login(bodyLogin)
            }

            Then("result error and return data") {
                verify(observerLogin, atLeastOnce()).onChanged(ResultState.Loading)
                viewModel.stateResponseLogin.test().assertValue {
                    it is ResultState.Error
                }
            }
        }

        afterFeature {
            TrampolineSchedulerRX.tearDown()
            InstantRuleExecution.tearDown()
        }
    }
})