package com.takusemba.jethub.repo

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.takusemba.jethub.base.ui.theme.JethubTheme
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.di.RepoAppModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class RepoFragment : Fragment(R.layout.fragment_repo) {

  @Inject
  lateinit var repoViewModelProviderFactory: RepoViewModelProviderFactory

  private val navigationViewModel: NavigationViewModel by activityViewModels()
  private val systemViewModel: SystemViewModel by activityViewModels()
  private val repoViewModel: RepoViewModel by viewModels { repoViewModelProviderFactory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DaggerRepoComponent.builder()
      .fragment(this)
      .appDependencies(
        EntryPointAccessors.fromApplication(
          requireContext().applicationContext,
          RepoAppModuleDependencies::class.java
        )
      )
      .build()
      .inject(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.findViewById<ComposeView>(R.id.compose_view).setContent {
      JethubTheme(systemViewModel.isNightMode()) {
        RepoScreen(repoViewModel, navigationViewModel)
      }
    }
  }
}
