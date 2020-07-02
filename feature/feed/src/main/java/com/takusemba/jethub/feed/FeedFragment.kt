package com.takusemba.jethub.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.takusemba.jethub.base.model.Direction
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.feed.databinding.FragmentFeedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed) {

  companion object {

    fun newInstance() = FeedFragment()
  }

  private val feedViewModel: FeedViewModel by viewModels(
    ownerProducer = { requireParentFragment() }
  )

  private val navigationViewModel: NavigationViewModel by activityViewModels()

  private val userViewModel: UserViewModel by activityViewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentFeedBinding.bind(view)

    val feedAdapter = FeedAdapter(userViewModel, feedViewModel, this)
    binding.pager.adapter = feedAdapter
    binding.tabLayout.setupWithViewPager(binding.pager)

    binding.account.setOnClickListener {
      navigationViewModel.onDirectionChanged(
        Direction.ACCOUNT)
    }
  }
}