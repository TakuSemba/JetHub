package com.takusemba.jethub.ui

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.takusemba.jethub.R
import com.takusemba.jethub.base.ui.LocalActivity
import com.takusemba.jethub.base.ui.theme.JethubTheme
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainTabFragment : Fragment(R.layout.fragment_main_tab) {

  private val systemViewModel: SystemViewModel by activityViewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.findViewById<ComposeView>(R.id.compose_view).setContent {
      JethubTheme(systemViewModel.isNightMode()) {
        CompositionLocalProvider(
          LocalActivity provides requireActivity()
        ) {
          JethubTheme {
            MainContent()
          }
        }
      }
    }
  }
}
