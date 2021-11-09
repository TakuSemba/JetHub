package com.takusemba.jethub.feed

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.feed.databinding.FragmentFeedChannelBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
      requireNotNull(ContextCompat.getDrawable(requireContext(), R.drawable.shape_divider))
    )
    binding.recyclerView.addItemDecoration(dividerItemDecoration)
    binding.recyclerView.setRecycledViewPool(recycledViewPool)
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = feedChannelAdapter

    lifecycleScope.launch {
      feedViewModel.uiState
        .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
        .collect { uiState ->
          val repos = uiState.hotRepos[language] ?: emptyList()
          feedChannelAdapter.submitList(repos)
          if (uiState.isLoading) {
            binding.progress.show()
          } else {
            binding.progress.hide()
          }
        }
    }
  }
}
