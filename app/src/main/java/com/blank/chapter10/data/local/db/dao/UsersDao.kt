package com.blank.chapter10.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.blank.chapter10.data.local.db.entity.User

@Dao
interface UsersDao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<User>
}