package com.example.myapplication.network

import com.blank.chapter10.data.model.BodyLogin
import com.blank.chapter10.data.model.ResponseLogin
import com.blank.chapter10.data.model.ResponseRegister
import com.blank.teamb_ex.BodyRegister
import com.blank.teamb_ex.LOGIN
import com.blank.teamb_ex.REGISTER
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(REGISTER)
    fun register(@Body bodyRegister: BodyRegister): Single<ResponseRegister>

    @POST(LOGIN)
    fun login(@Body bodyLogin: BodyLogin): Single<ResponseLogin>
}