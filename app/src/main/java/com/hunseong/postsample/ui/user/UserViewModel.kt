package com.hunseong.postsample.ui.user

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hunseong.postsample.data.model.User
import com.hunseong.postsample.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    state: SavedStateHandle,
    private val userRepository: UserRepository
) : ViewModel() {

    val user: User = state.get("user")!!

    val isLiked: StateFlow<Boolean> = userRepository.getUserExist(user.id)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    fun toggleLikeButton() {
        viewModelScope.launch {
            if (isLiked.value) {
                userRepository.deleteUser(user)
            } else {
                userRepository.insertUser(user)
            }
        }
    }
}