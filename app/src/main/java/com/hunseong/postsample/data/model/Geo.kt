package com.hunseong.postsample.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Geo(
    @field:Json(name = "lat")
    val lat: String,
    @field:Json(name = "lng")
    val lng: String
) : Parcelable