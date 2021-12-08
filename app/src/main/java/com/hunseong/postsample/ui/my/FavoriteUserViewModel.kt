package com.hunseong.postsample.ui.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.postsample.data.model.Result
import com.hunseong.postsample.data.model.User
import com.hunseong.postsample.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteUserViewModel @Inject constructor(
    favoriteRepository: FavoriteRepository,
) : ViewModel() {

    val users: StateFlow<Result<List<User>>> =
        favoriteRepository.getAllUsers()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.Uninitialized
            )
}