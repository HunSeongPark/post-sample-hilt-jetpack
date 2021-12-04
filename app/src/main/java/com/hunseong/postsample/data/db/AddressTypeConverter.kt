package com.hunseong.postsample.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.hunseong.postsample.data.model.Address
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

@ProvidedTypeConverter
class AddressTypeConverter @Inject constructor(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): Address? {
        val adapter: JsonAdapter<Address> = moshi.adapter(Address::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromAddress(type: Address): String {
        val adapter: JsonAdapter<Address> = moshi.adapter(Address::class.java)
        return adapter.toJson(type)
    }
}