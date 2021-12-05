package com.hunseong.postsample.data.network

import com.hunseong.postsample.data.model.Comment
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentApiService {

    @GET("comments")
    suspend fun getComments(
        @Query("postId") postId: Long,
    ): List<Comment>
}