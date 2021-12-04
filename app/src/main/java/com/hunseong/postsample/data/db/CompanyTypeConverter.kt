package com.hunseong.postsample.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.hunseong.postsample.data.model.Company
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

@ProvidedTypeConverter
class CompanyTypeConverter @Inject constructor(
    private val moshi: Moshi,
) {

    @TypeConverter
    fun fromString(value: String): Company? {
        val adapter: JsonAdapter<Company> = moshi.adapter(Company::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromCompany(type: Company): String {
        val adapter: JsonAdapter<Company> = moshi.adapter(Company::class.java)
        return adapter.toJson(type)
    }
}