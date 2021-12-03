package com.hunseong.postsample.data.network

import com.hunseong.postsample.data.model.Post
import retrofit2.http.GET

interface PostApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}