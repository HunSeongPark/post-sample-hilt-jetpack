package com.hunseong.postsample.di

import android.content.Context
import android.content.SharedPreferences
import com.hunseong.postsample.data.preferences.PreferencesManager
import com.hunseong.postsample.data.preferences.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PreferencesModule {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context) : SharedPreferences {
        return context.getSharedPreferences("Profile",Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferencesManager(preferences: SharedPreferences) : SharedPreferencesManager {
        return SharedPreferencesManager(preferences)
    }
}