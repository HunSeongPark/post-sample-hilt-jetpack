package com.hunseong.postsample.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hunseong.postsample.databinding.FragmentFavoritePostBinding
import com.hunseong.postsample.databinding.FragmentFavoriteUserBinding
import com.hunseong.postsample.ui.adapter.PostAdapter
import com.hunseong.postsample.ui.adapter.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FavoriteUserFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteUserBinding

    private val viewModel: FavoriteUserViewModel by viewModels()

    private val userAdapter: UserAdapter by lazy {
        UserAdapter {
            val directions = MyFragmentDirections.myFragmentToUserFragment(it)
            findNavController().navigate(directions)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteUserBinding.inflate(inflater, container, false).apply {
            recyclerView.adapter = userAdapter
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }
}