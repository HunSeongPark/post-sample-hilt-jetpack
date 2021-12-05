package com.hunseong.postsample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DispatcherModule {

    @Provides
    @Singleton
    fun provideIODispatcher() : CoroutineDispatcher {
        return Dispatchers.IO
    }
}