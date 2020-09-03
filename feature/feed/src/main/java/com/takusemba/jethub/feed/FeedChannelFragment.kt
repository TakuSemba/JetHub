package com.takusemba.jethub.feed

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.feed.databinding.FragmentFeedChannelBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeedChannelFragment : Fragment(R.layout.fragment_feed_channel) {

  companion object {

    const val KEY_LANGUAGE = "key_language"

    fun newInstance(language: String): FeedChannelFragment = FeedChannelFragment().apply {
      arguments = bundleOf(KEY_LANGUAGE to language)
    }
  }

  @Inject lateinit var recycledViewPool: RecyclerView.RecycledViewPool

  private val feedViewModel: FeedViewModel by viewModels(
    ownerProducer = { requireParentFragment().requireParentFragment() }
  )
  private val userViewModel: UserViewModel by activityViewModels()
  private val navigationViewModel: NavigationViewModel by activityViewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentFeedChannelBinding.bind(view)
    val language = requireNotNull(requireArguments().getString(KEY_LANGUAGE))

    val linearLayoutManager = LinearLayoutManager(context)
    val feedChannelAdapter = FeedChannelAdapter(userViewModel, navigationViewModel)
    val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
    dividerItemDecoration.setDrawable(
      requireNotNull(requireContext().getDrawable(R.drawable.shape_divider))
    )
    with(binding.recyclerView) {
      addItemDecoration(dividerItemDecoration)
      setRecycledViewPool(recycledViewPool)
      layoutManager = linearLayoutManager
      adapter = feedChannelAdapter
    }

    binding.progress.show()
    feedViewModel.hotRepos(language).observe(viewLifecycleOwner) {
      binding.progress.hide()
    }

    feedViewModel.hotRepos(language).observe(viewLifecycleOwner) { repositories ->
      feedChannelAdapter.submitList(repositories)
    }
  }
}
