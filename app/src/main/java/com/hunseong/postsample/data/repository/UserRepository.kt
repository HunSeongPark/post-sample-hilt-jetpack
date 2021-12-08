package com.hunseong.postsample.data.repository

import com.hunseong.postsample.data.db.UserDao
import com.hunseong.postsample.data.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) : Repository {

    fun getUserExist(id: Long) = userDao.getUserExist(id)

    suspend fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
}