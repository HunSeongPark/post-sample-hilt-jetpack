package com.hunseong.postsample.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class Post(
    @field:Json(name = "id") @PrimaryKey val id: Long,
    @field:Json(name = "userId") val userId: Long,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "body") val body: String,
): Parcelable
