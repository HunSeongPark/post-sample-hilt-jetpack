package com.hunseong.postsample.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.postsample.data.model.Post
import com.hunseong.postsample.data.model.PostInfo
import com.hunseong.postsample.data.model.Result
import com.hunseong.postsample.data.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    state: SavedStateHandle,
    private val detailRepository: DetailRepository,
) : ViewModel() {
    val post: Post = state.get("post")!!

    val isLiked: StateFlow<Boolean> = detailRepository.getPostExist(post.id)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    val postInfo: MutableStateFlow<Result<PostInfo>> = MutableStateFlow(Result.Loading)

    fun toggleLikeButton() {
        viewModelScope.launch {
            if (isLiked.value) {
                detailRepository.deletePost(post)
            } else {
                detailRepository.insertPost(post)
            }
        }
    }

    fun loadPostInfo() {
        viewModelScope.launch {
            detailRepository.getComments(post.id)
                .zip(detailRepository.getUser(post.userId)) { comments, user ->
                    Result.Success(PostInfo(comments, user))
                }.catch { e -> postInfo.value = Result.Error(e) }.collect {
                    postInfo.value = it
                }
        }
    }
}