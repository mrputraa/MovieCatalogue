package com.example.nontonkuy.di

import android.content.Context
import com.example.nontonkuy.data.source.local.LocalDataSource
import com.example.nontonkuy.data.source.local.room.NontonKuyDatabase
import com.example.nontonkuy.data.source.remote.RemoteDataSource
import com.example.nontonkuy.data.source.remote.Repository
import com.example.nontonkuy.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): Repository {

        val database = NontonKuyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.dao())
        val appExecutors = AppExecutors()

        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}