package com.blank.chapter10.di.modul

import android.content.Context
import androidx.room.Room
import com.blank.chapter10.data.local.db.AppDatabase
import com.blank.chapter10.data.local.db.AppDbHelper
import com.blank.chapter10.data.local.db.DbHelper
import com.blank.framework.di.DatabaseInfo
import com.blank.teamb_ex.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
class DbModule {
    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String = DB_NAME

    @Provides
    fun provideAppDatabase(
        @DatabaseInfo dbName: String,
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            dbName
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper = appDbHelper
}