package com.blank.chapter10.data.local.db

import androidx.lifecycle.LiveData
import com.blank.chapter10.data.local.db.entity.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDbHelper @Inject constructor(private val appDatabase: AppDatabase) : DbHelper {
    override fun insertUser(user: User): Observable<Boolean> =
        Observable.fromCallable {
            appDatabase.usersDao().insertUser(user)
            true
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    override fun getUsers(): LiveData<User> = appDatabase.usersDao().getUsers()
}