package com.hunseong.postsample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hunseong.postsample.data.model.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao() : PostDao
}