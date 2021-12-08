package com.hunseong.postsample.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hunseong.postsample.data.model.PostInfo
import com.hunseong.postsample.data.model.Result
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

            userLayout.setOnClickListener {
                val user = (viewModel.postInfo.value as Result.Success<PostInfo>).data.user
                val directions = DetailFragmentDirections.detailFragmentToUserFragment(user)
                findNavController().navigate(directions)
            }
        }

        return binding.root
    }
}