package com.takusemba.jethub.feed

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.takusemba.jethub.base.model.Language
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.feed.databinding.FragmentFeedChannelBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedChannelFragment : Fragment(R.layout.fragment_feed_channel) {

  companion object {

    private const val KEY_LANGUAGE = "key_language"

    fun newInstance(language: String): FeedChannelFragment = FeedChannelFragment().apply {
      arguments = bundleOf(KEY_LANGUAGE to language)
    }
  }

  private val feedViewModel: FeedViewModel by activityViewModels()
  private val userViewModel: UserViewModel by activityViewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentFeedChannelBinding.bind(view)
    val languageName = requireArguments().getString(KEY_LANGUAGE)
    val language = Language.of(languageName)
    val feedRepoSection = FeedRepoSection(language, this, feedViewModel, userViewModel)

    val linearLayoutManager = LinearLayoutManager(context)
    val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
      add(feedRepoSection)
    }
    val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
    dividerItemDecoration.setDrawable(
      requireNotNull(requireContext().getDrawable(R.drawable.shape_divider)))
    binding.recyclerView.addItemDecoration(dividerItemDecoration)
    binding.recyclerView.layoutManager = linearLayoutManager
    binding.recyclerView.adapter = groupAdapter

    binding.progress.show()
    feedViewModel.hotRepos(language).observe(viewLifecycleOwner) {
      binding.progress.hide()
    }
  }
}