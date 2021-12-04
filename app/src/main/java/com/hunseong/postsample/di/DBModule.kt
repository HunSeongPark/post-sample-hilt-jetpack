package com.hunseong.postsample.di

import android.content.Context
import androidx.room.Room
import com.hunseong.postsample.data.db.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    fun provideAppDatabase(@ApplicationContext context: Context,
    addressTypeConverter: AddressTypeConverter,
    companyTypeConverter: CompanyTypeConverter): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "post.db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(addressTypeConverter)
            .addTypeConverter(companyTypeConverter)
            .build()
    }

    @Provides
    @Singleton
    fun providePostDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideMoshi() : Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAddressTypeConverter(moshi: Moshi): AddressTypeConverter {
        return AddressTypeConverter(moshi)
    }

    @Provides
    @Singleton
    fun provideCompanyTypeConverter(moshi: Moshi): CompanyTypeConverter {
        return CompanyTypeConverter(moshi)
    }
}