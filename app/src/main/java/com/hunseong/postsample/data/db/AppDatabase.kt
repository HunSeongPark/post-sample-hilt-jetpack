package com.hunseong.postsample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hunseong.postsample.data.model.Post
import com.hunseong.postsample.data.model.User

@Database(entities = [Post::class, User::class], version = 1, exportSchema = false)
@TypeConverters(value = [AddressTypeConverter::class, CompanyTypeConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao() : PostDao
    abstract fun userDao() : UserDao
}