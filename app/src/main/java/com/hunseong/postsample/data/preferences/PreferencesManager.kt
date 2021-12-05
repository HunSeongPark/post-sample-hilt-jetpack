package com.hunseong.postsample.data.preferences

interface PreferencesManager {
    fun getName(key: String) : String?

    fun setName(key: String, name: String)
}