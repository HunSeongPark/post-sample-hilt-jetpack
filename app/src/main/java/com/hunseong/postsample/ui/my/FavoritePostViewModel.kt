package com.hunseong.postsample.ui.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.postsample.data.model.Post
import com.hunseong.postsample.data.model.Result
import com.hunseong.postsample.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FavoritePostViewModel @Inject constructor(
    favoriteRepository: FavoriteRepository
) : ViewModel() {

    val posts: StateFlow<Result<List<Post>>> =
        favoriteRepository.getAllPosts()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.Uninitialized
            )
}