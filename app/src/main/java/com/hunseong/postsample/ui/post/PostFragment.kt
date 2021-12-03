package com.hunseong.postsample.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.OnSwipe
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hunseong.postsample.databinding.FragmentPostBinding
import com.hunseong.postsample.ui.adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentPostBinding

    private val viewModel: PostViewModel by viewModels()

    private val postAdapter: PostAdapter by lazy {
        PostAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = postAdapter
            vm = viewModel
            swipeLayout.setOnRefreshListener(this@PostFragment)
        }

        viewModel.getAllPosts()

        return binding.root
    }

    override fun onRefresh() {
        viewModel.getAllPosts()
    }
}