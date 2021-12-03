package com.hunseong.postsample.binding

import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hunseong.postsample.R
import com.hunseong.postsample.data.model.Result

object ViewBinding {

    @JvmStatic
    @BindingAdapter("isLoading")
    fun bindIsLoading(view: SwipeRefreshLayout, result: Result<*>) {
        view.isRefreshing = result is Result.Loading
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
}