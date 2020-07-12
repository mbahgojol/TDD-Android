package com.blank.chapter10.data.remote

import com.blank.chapter10.data.model.BodyLogin
import com.blank.chapter10.utils.api.ResultState
import com.blank.chapter10.utils.rx.singleIo
import com.blank.teamb_ex.BodyRegister
import com.example.myapplication.network.ApiServices
import io.reactivex.Single

class AppApiHelper(private val apiServices: ApiServices) : ApiHelper {
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