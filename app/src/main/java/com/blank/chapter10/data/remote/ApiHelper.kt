package com.blank.chapter10.data.remote

import com.blank.chapter10.data.model.BodyLogin
import com.blank.chapter10.utils.api.ResultState
import com.blank.teamb_ex.BodyRegister
import io.reactivex.Single

interface ApiHelper {
    fun register(bodyRegister: BodyRegister): Single<ResultState>
    fun login(bodyLogin: BodyLogin): Single<ResultState>
}