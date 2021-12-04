package com.hunseong.postsample.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hunseong.postsample.R
import com.hunseong.postsample.data.model.PostInfo
import com.hunseong.postsample.data.model.Result

object ViewBinding {

    @JvmStatic
    @BindingAdapter("isLoading")
    fun bindIsLoading(view: View, result: Result<*>) {
        when (view) {
            is SwipeRefreshLayout -> {
                view.isRefreshing = result is Result.Loading
            }
            else -> {
                view.isVisible = result is Result.Loading
            }
        }

    }

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, result: Result<*>) {
        if (result is Result.Error) {
            if (result.isNetworkError) {
                Toast.makeText(view.context, R.string.network_error, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, R.string.something_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("backPressed")
    fun bindBackPressed(view: View, isEnabled: Boolean) {
        if (isEnabled) {
            view.setOnClickListener {
                view.findNavController().navigateUp()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setLikeButton")
    fun bindSetLikeButton(view: ImageView, isLiked: Boolean) {
        val imageResource =
            if (isLiked) R.drawable.ic_like_active else R.drawable.ic_like_inactive
        view.setImageResource(imageResource)
    }

    @JvmStatic
    @BindingAdapter("setUserText")
    fun bindSetUserText(view: TextView, result: Result<*>) {
        if (result is Result.Success<*>) {
            view.text = (result.data as PostInfo).user.name
        }
    }
}