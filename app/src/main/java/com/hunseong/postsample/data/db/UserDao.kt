package com.hunseong.postsample.data.db

import androidx.room.*
import com.hunseong.postsample.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT EXISTS(SELECT * FROM User WHERE id = :id_)")
    fun getUserExist(id_: Long): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): Flow<List<User>>
}