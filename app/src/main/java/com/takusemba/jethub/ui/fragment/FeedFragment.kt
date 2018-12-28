package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentFeedBinding
import com.takusemba.jethub.extension.activityViewModelProvider
import com.takusemba.jethub.extension.viewModelProvider
import com.takusemba.jethub.ui.item.FeedSection
import com.takusemba.jethub.viewmodel.FeedViewModel
import com.takusemba.jethub.viewmodel.UserViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FeedFragment : DaggerFragment() {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val userViewModel: UserViewModel by lazy {
    activityViewModelProvider(viewModelFactory) as UserViewModel
  }

  private val feedViewModel: FeedViewModel by lazy {
    viewModelProvider(viewModelFactory) as FeedViewModel
  }

  private val feedSection: FeedSection by lazy {
    FeedSection(this, feedViewModel)
  }

  companion object {

    fun newInstance() = FeedFragment()
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

    val linearLayoutManager = LinearLayoutManager(context)
    val groupAdapter = GroupAdapter<ViewHolder>().apply {
      add(feedSection)
    }
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = groupAdapter
  }
}