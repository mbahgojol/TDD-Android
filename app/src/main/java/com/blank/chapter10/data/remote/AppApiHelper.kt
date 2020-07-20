package com.blank.chapter10.data.remote

import com.blank.chapter10.data.model.BodyLogin
import com.blank.chapter10.utils.api.ResultState
import com.blank.chapter10.utils.rx.singleIo
import com.blank.teamb_ex.BodyRegister
import com.example.myapplication.network.ApiService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiHelper @Inject constructor(private val apiServices: ApiService) : ApiHelper {
    override fun register(bodyRegister: BodyRegister): Single<ResultState> =
        apiServices.register(bodyRegister)
            .map {
                ResultState.Success(it, "")
            }.compose(singleIo())

    override fun login(bodyLogin: BodyLogin): Single<ResultState> =
        apiServices.login(bodyLogin)
            .map {
                ResultState.Success(it, "")
            }.compose(singleIo())


}