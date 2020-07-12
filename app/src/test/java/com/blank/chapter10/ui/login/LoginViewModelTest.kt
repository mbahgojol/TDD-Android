package com.blank.chapter10.ui.login

import androidx.lifecycle.MutableLiveData
import com.blank.chapter10.data.AppDataManager
import com.blank.chapter10.data.model.BodyLogin
import com.blank.chapter10.data.model.ResponseLogin
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Spy

class LoginViewModelTest {

    @Mock
    private val appDataManager = mock(AppDataManager::class.java)

    private lateinit var viewModel: LoginViewModel
    @Mock
    private lateinit var view: LoginNavigator
    private lateinit var body: BodyLogin

    @Spy
    private var responseLiveData: MutableLiveData<ResponseLogin> = MutableLiveData()

    @Before
    fun setUp() {
        view = mock(LoginNavigator::class.java)
        viewModel = mock(LoginViewModel::class.java)
        viewModel.navigator = view

        body = BodyLogin(
            "123123",
            "kampreto@gmail.com"
        )
    }

    @Test
    fun login() {
        /*`when`(
            viewModel.login(

            )
        ).thenReturn()*/
        viewModel.login(body)
        verify(viewModel).login(body)
    }

    @Test
    fun `show loading when login`() {
        viewModel.login(body)
        verify(view).showLoading()
    }

    @Test
    fun `test livedata`() {

    }
}