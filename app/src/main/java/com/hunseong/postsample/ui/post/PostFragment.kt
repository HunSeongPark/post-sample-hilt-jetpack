package com.hunseong.postsample.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hunseong.postsample.databinding.FragmentPostBinding
import com.hunseong.postsample.ui.adapter.PostAdapter
import com.hunseong.postsample.ui.dialog.ProfileDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentPostBinding

    private val viewModel: PostViewModel by viewModels()

    private val postAdapter: PostAdapter by lazy {
        PostAdapter {
            val directions = PostFragmentDirections.postFragmentToDetailFragment(it)
            findNavController().navigate(directions)
        }
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

        observeData()

        viewModel.getAllPosts()

        return binding.root
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.name.collect {
                    if (!viewModel.isCheckProfile) {
                        when (it) {
                            "unknown" -> Unit
                            null -> showProfileDialog()
                            else -> {
                                Toast.makeText(this@PostFragment.context,
                                    "환영합니다, ${viewModel.name.value}님!",
                                    Toast.LENGTH_SHORT)
                                    .show()
                                viewModel.isCheck()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showProfileDialog() {
        ProfileDialog(requireContext()) {
            viewModel.setName(it)
        }.show()
    }

    override fun onRefresh() {
        viewModel.getAllPosts()
    }
}