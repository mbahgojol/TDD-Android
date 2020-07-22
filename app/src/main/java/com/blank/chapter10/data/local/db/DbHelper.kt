package com.blank.chapter10.data.local.db

import androidx.lifecycle.LiveData
import com.blank.chapter10.data.local.db.entity.User
import io.reactivex.Observable

interface DbHelper {
    fun insertUser(user: User): Observable<Boolean>
    fun getUsers(): LiveData<User>
}