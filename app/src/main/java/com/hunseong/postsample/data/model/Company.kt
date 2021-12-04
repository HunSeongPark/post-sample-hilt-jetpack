package com.hunseong.postsample.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Company(
    @field:Json(name = "bs")
    val bs: String,
    @field:Json(name = "catchPhrase")
    val catchPhrase: String,
    @field:Json(name = "name")
    val name: String
) : Parcelable