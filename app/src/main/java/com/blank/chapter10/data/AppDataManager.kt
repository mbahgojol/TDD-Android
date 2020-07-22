package com.blank.chapter10.data

import androidx.lifecycle.LiveData
import com.blank.chapter10.data.local.db.DbHelper
import com.blank.chapter10.data.local.db.entity.User
import com.blank.chapter10.data.model.BodyLogin
import com.blank.chapter10.data.remote.ApiHelper
import com.blank.teamb_ex.BodyRegister
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val apiHelper: ApiHelper,
    private val dbHelper: DbHelper
) : DataManager {
    override fun register(bodyRegister: BodyRegister) = apiHelper.register(bodyRegister)
    override fun login(bodyLogin: BodyLogin) = apiHelper.login(bodyLogin)
    override fun insertUser(user: User): Observable<Boolean> = dbHelper.insertUser(user)
    override fun getUsers(): LiveData<User> = dbHelper.getUsers()
}