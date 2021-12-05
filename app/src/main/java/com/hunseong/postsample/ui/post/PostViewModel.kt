package com.hunseong.postsample.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.postsample.data.model.Post
import com.hunseong.postsample.data.model.Result
import com.hunseong.postsample.data.preferences.SharedPreferencesManager
import com.hunseong.postsample.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val preferencesManager: SharedPreferencesManager,
) : ViewModel() {

    private val _name: MutableStateFlow<String?> = MutableStateFlow("unknown")
    val name = _name.asStateFlow()

    private var _isCheckProfile = false
    val isCheckProfile
        get() = _isCheckProfile

    init {
        _name.value = preferencesManager.getName(USER_NAME_KEY)
    }

    private val _posts: MutableStateFlow<Result<List<Post>>> =
        MutableStateFlow(Result.Uninitialized)
    val posts = _posts.asStateFlow()

    fun getAllPosts() = viewModelScope.launch {
        postRepository.getPosts().collect {
            _posts.value = it
        }
    }

    fun setName(name: String) {
        preferencesManager.setName(USER_NAME_KEY, name)
        _name.value = preferencesManager.getName(USER_NAME_KEY)
    }

    fun isCheck() {
        _isCheckProfile = true
    }


    companion object {
        const val USER_NAME_KEY = "user_name_key"
    }
}