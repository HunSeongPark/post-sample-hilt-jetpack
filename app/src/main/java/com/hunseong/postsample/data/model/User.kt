package com.hunseong.postsample.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
@Entity
data class User(
    @field:Json(name = "address")
    val address: Address,
    @field:Json(name = "company")
    val company: Company,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "id")
    @PrimaryKey val id: Long,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "phone")
    val phone: String,
    @field:Json(name = "username")
    val username: String,
    @field:Json(name = "website")
    val website: String,
) : Parcelable