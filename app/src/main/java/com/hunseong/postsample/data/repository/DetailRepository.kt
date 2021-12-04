package com.hunseong.postsample.data.repository

import com.hunseong.postsample.data.db.PostDao
import com.hunseong.postsample.data.model.Post
import com.hunseong.postsample.data.model.Result
import com.hunseong.postsample.data.network.CommentApiService
import com.hunseong.postsample.data.network.UserApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val postDao: PostDao,
    private val commentApiService: CommentApiService,
    private val userApiService: UserApiService,
) : Repository {

    fun getPostExist(id: Long) = postDao.getPostExist(id)

    fun getComments(id: Long) = flow {
        val comments = commentApiService.getComments(id)
        emit(comments)
    }

    fun getUser(userId: Long) = flow {
        val user = userApiService.getUser(userId)
        emit(user)
    }

    suspend fun insertPost(post: Post) = postDao.insertPost(post)

    suspend fun deletePost(post: Post) = postDao.deletePost(post)

}