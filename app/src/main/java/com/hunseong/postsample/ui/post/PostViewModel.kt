package com.hunseong.postsample.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.postsample.data.model.Post
import com.hunseong.postsample.data.model.Result
import com.hunseong.postsample.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    private val _posts: MutableStateFlow<Result<List<Post>>> =
        MutableStateFlow(Result.Uninitialized)
    val posts = _posts.asStateFlow()

    fun getAllPosts() = viewModelScope.launch {
        postRepository.getPosts().collect {
            _posts.value = it
        }
    }
}