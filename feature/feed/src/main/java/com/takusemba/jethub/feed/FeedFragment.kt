package com.takusemba.jethub.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.tabs.TabLayoutMediator
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.feed.databinding.FragmentFeedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed) {

  companion object {

    fun newInstance() = FeedFragment()
  }

  private val systemViewModel: SystemViewModel by activityViewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentFeedBinding.bind(view)

    val adapter = FeedAdapter(this)
    binding.pager.adapter = adapter
    val mediator = TabLayoutMediator(binding.tabLayout, binding.pager) { tab: Tab, position: Int ->
      tab.text = adapter.getTitle(position)
    }
    mediator.attach()

    binding.themeSwitch.setOnClickListener {
      systemViewModel.setNightMode(!systemViewModel.isNightMode())
    }
  }
}
