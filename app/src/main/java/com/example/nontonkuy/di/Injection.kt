package com.example.nontonkuy.di

import android.content.Context
import com.example.nontonkuy.data.source.remote.RemoteDataSource
import com.example.nontonkuy.data.source.remote.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return Repository.getInstance(remoteDataSource)
    }
}