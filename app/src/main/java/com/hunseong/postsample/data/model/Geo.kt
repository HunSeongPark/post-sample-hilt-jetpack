package com.hunseong.postsample.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Geo(
    @field:Json(name = "lat")
    val lat: String?,
    @field:Json(name = "lng")
    val lng: String?
)