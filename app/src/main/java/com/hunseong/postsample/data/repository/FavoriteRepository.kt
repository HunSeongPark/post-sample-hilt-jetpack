package com.hunseong.postsample.data.repository

import com.hunseong.postsample.data.db.PostDao
import com.hunseong.postsample.data.db.UserDao
import com.hunseong.postsample.data.model.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val postDao: PostDao,
    private val userDao: UserDao,
) : Repository {

    fun getAllPosts() = flow {
        emit(Result.Loading)
        postDao.getAllPosts().collect {
            if (it.isEmpty()) {
                emit(Result.Empty)
            } else {
                emit(Result.Success(it))
            }
        }
    }.catch { e ->
        emit(Result.Error(e))
    }

    fun getAllUsers() = flow {
        emit(Result.Loading)
        userDao.getAllUsers().collect {
            if (it.isEmpty()) {
                emit(Result.Empty)
            } else {
                emit(Result.Success(it))
            }
        }
    }.catch { e -> emit(Result.Error(e)) }
}