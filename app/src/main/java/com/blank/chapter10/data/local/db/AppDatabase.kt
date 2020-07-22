package com.blank.chapter10.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blank.chapter10.data.local.db.dao.UsersDao
import com.blank.chapter10.data.local.db.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}