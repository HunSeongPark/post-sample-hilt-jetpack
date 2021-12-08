package com.hunseong.postsample.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hunseong.postsample.databinding.FragmentFavoritePostBinding
import com.hunseong.postsample.ui.adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritePostFragment : Fragment() {

    private lateinit var binding: FragmentFavoritePostBinding

    private val viewModel: FavoritePostViewModel by viewModels()

    private val postAdapter: PostAdapter by lazy {
        PostAdapter {
            val directions = MyFragmentDirections.myFragmentToDetailFragment(it)
            findNavController().navigate(directions)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritePostBinding.inflate(inflater, container, false).apply {
            recyclerView.adapter = postAdapter
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }
}