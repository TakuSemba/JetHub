package com.takusemba.jethub.feed

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.takusemba.jethub.base.ui.theme.JethubTheme
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed) {

  companion object {

    fun newInstance() = FeedFragment()
  }

  private val feedViewModel: FeedViewModel by viewModels(
    ownerProducer = { requireParentFragment().requireParentFragment() }
  )
  private val systemViewModel: SystemViewModel by activityViewModels()
  private val userViewModel: UserViewModel by activityViewModels()
  private val navigationViewModel: NavigationViewModel by activityViewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.findViewById<ComposeView>(R.id.compose_view).setContent {
      JethubTheme(systemViewModel.isNightMode()) {
        FeedScreen(feedViewModel, systemViewModel, userViewModel, navigationViewModel)
      }
    }
  }
}
