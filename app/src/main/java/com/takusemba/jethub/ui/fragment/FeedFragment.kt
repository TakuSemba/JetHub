package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentFeedBinding
import com.takusemba.jethub.ui.adapter.FeedAdapter
import com.takusemba.jethub.viewmodel.FeedViewModel
import com.takusemba.jethub.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed) {

  companion object {

    fun newInstance() = FeedFragment()
  }

  private val feedViewModel: FeedViewModel by viewModels(
    ownerProducer = { requireParentFragment() }
  )

  private val userViewModel: UserViewModel by activityViewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentFeedBinding.bind(view)

    val feedAdapter = FeedAdapter(userViewModel, feedViewModel, this)
    binding.pager.adapter = feedAdapter
    binding.tabLayout.setupWithViewPager(binding.pager)
  }
}