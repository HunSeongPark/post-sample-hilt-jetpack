package com.hunseong.postsample.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hunseong.postsample.data.model.Post
import com.hunseong.postsample.data.model.Result
import com.hunseong.postsample.ui.adapter.PostAdapter

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, result: Result<*>) {
        if (result is Result.Success) {
            when (view.adapter) {
                is PostAdapter -> {
                    (view.adapter as ListAdapter<Any, *>).submitList(result.data as List<Post>)
                }
                // 추후 새로운 adapter 생성 시 is 및 type casting 으로 확장 가능
            }
        }
    }

}