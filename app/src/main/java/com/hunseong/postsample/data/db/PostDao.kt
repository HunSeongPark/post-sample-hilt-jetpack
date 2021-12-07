package com.hunseong.postsample.data.db

import androidx.room.*
import com.hunseong.postsample.data.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT EXISTS(SELECT * FROM Post WHERE id = :id_)")
    fun getPostExist(id_: Long): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post)

    @Delete
    suspend fun deletePost(post: Post)

    @Query("SELECT * FROM Post")
    fun getAllPosts(): Flow<List<Post>>

}