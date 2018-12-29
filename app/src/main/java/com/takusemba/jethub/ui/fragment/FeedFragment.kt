package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentFeedBinding
import com.takusemba.jethub.extension.parentViewModelProvider
import com.takusemba.jethub.ui.adapter.FeedAdapter
import com.takusemba.jethub.viewmodel.FeedViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FeedFragment : DaggerFragment() {

  companion object {

    fun newInstance() = FeedFragment()
  }

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val feedViewModel: FeedViewModel by lazy {
    parentViewModelProvider(viewModelFactory) as FeedViewModel
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.fragment_feed, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = DataBindingUtil.bind<FragmentFeedBinding>(view)!!

    val feedAdapter = FeedAdapter(feedViewModel, this)
    binding.pager.adapter = feedAdapter
    binding.tabLayout.setupWithViewPager(binding.pager)
  }
}