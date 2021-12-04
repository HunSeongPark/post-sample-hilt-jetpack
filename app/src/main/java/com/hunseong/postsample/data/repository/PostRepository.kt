package com.hunseong.postsample.data.repository

import com.hunseong.postsample.data.model.Result
import com.hunseong.postsample.data.network.PostApiService
import com.hunseong.postsample.data.network.UserApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postApiService: PostApiService,
) : Repository {

    fun getPosts() = flow {
        emit(Result.Loading)
        val posts = postApiService.getPosts()
        emit(Result.Success(posts))
    }.catch { e -> emit(Result.Error(e)) }
}