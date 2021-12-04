package com.hunseong.postsample.di

import android.content.Context
import androidx.room.Room
import com.hunseong.postsample.data.db.AppDatabase
import com.hunseong.postsample.data.db.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "post.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePostDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }
}