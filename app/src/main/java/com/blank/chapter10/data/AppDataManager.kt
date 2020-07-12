package com.blank.chapter10.data

import com.blank.chapter10.data.model.BodyLogin
import com.blank.chapter10.data.remote.ApiHelper
import com.blank.teamb_ex.BodyRegister

class AppDataManager(private val apiHelper: ApiHelper) : DataManager {
    override fun register(bodyRegister: BodyRegister) = apiHelper.register(bodyRegister)
    override fun login(bodyLogin: BodyLogin) = apiHelper.login(bodyLogin)
}