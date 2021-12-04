package com.hunseong.postsample.data.network

import com.hunseong.postsample.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {

    @GET("users/{userId}")
    suspend fun getUser(
        @Path("userId") userId: Long,
    ): User
}