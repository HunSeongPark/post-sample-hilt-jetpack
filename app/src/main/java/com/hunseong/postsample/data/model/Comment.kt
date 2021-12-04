package com.hunseong.postsample.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Comment(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "postId") val postId: Long,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "body") val body: String,
)