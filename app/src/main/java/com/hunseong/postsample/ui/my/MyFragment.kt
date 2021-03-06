package com.hunseong.postsample.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hunseong.postsample.R
import com.hunseong.postsample.data.preferences.SharedPreferencesManager
import com.hunseong.postsample.databinding.FragmentMyBinding
import com.hunseong.postsample.ui.adapter.MyPagerAdapter
import com.hunseong.postsample.ui.adapter.MyPagerAdapter.Companion.POST_PAGE_INDEX
import com.hunseong.postsample.ui.adapter.MyPagerAdapter.Companion.USER_PAGE_INDEX
import com.hunseong.postsample.ui.post.PostViewModel.Companion.USER_NAME_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyFragment : Fragment() {

    private lateinit var binding: FragmentMyBinding

    @Inject
    lateinit var preferencesManager: SharedPreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyBinding.inflate(inflater, container, false).apply {
            name = getString(R.string.my_toolbar, preferencesManager.getName(USER_NAME_KEY))
            pager.adapter = MyPagerAdapter(this@MyFragment)
        }

        setTabLayout()
        return binding.root
    }

    private fun setTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            val iconAndText = getTabIconAndText(position)
            tab.setIcon(iconAndText.first)
            tab.setText(iconAndText.second)
        }.attach()
    }

    private fun getTabIconAndText(position: Int): Pair<Int, Int> {
        return when(position) {
            POST_PAGE_INDEX -> R.drawable.post_tab_selector to R.string.post
            USER_PAGE_INDEX -> R.drawable.user_tab_selector to R.string.my
            else -> throw IndexOutOfBoundsException()
        }
    }
}