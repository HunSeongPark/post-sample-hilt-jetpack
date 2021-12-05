package com.hunseong.postsample.data.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(
    private val preferences: SharedPreferences
) : PreferencesManager {

    override fun getName(key: String): String? {
        return preferences.getString(key, null)
    }

    override fun setName(key: String, name: String) {
        preferences.edit {
            putString(key, name)
        }
    }
}