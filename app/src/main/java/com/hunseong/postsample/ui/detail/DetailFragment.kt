package com.hunseong.postsample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hunseong.postsample.databinding.FragmentDetailBinding
import com.hunseong.postsample.ui.adapter.CommentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by viewModels()

    private val commentAdapter: CommentAdapter by lazy {
        CommentAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            recyclerView.adapter = commentAdapter

            likeBtn.setOnClickListener {
                viewModel.toggleLikeButton()
            }
        }

        viewModel.loadPostInfo()

        return binding.root
    }
}