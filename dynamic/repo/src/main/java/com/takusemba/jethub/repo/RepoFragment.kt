package com.takusemba.jethub.repo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.compose.Recomposer
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxHeight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextAlign
import androidx.ui.unit.dp
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.compose.JethubTheme
import com.takusemba.jethub.di.RepoModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class RepoFragment : Fragment(R.layout.fragment_repo) {

  @Inject lateinit var repoViewModelProviderFactory: RepoViewModelProviderFactory

  private val navigationViewModel: NavigationViewModel by activityViewModels()
  private val repoViewModel: RepoViewModel by viewModels { repoViewModelProviderFactory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DaggerRepoComponent.builder()
      .fragment(this)
      .appDependencies(
        EntryPointAccessors.fromApplication(
          requireContext().applicationContext,
          RepoModuleDependencies::class.java
        )
      )
      .build()
      .inject(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    (view as ViewGroup).setContent(Recomposer.current()) {
      JethubTheme {
        Column(
          modifier = Modifier.fillMaxWidth().fillMaxHeight(),
          verticalArrangement = Arrangement.Center,
          horizontalGravity = Alignment.CenterHorizontally
        ) {
          Box(
            gravity = ContentGravity.Center
          ) {
            Text(
              text = "Repo Fragment",
              fontWeight = FontWeight.Bold,
              textAlign = TextAlign.Center,
              modifier = Modifier.padding(22.dp)
            )
            Text(
              text = "This screen is under development...",
              textAlign = TextAlign.Center,
              modifier = Modifier.padding(16.dp)
            )
            Button(
              onClick = {
                val owner = RepoFragmentArgs.fromBundle(requireArguments()).ownerName
                navigationViewModel.openDeveloper(owner)
              }
            ) {
              Text(
                text = "Go to Developer Fragment",
                modifier = Modifier.padding(18.dp)
              )
            }
          }
        }
      }
    }

    repoViewModel.repository.observe(viewLifecycleOwner) { repository ->
      Log.d("TEST", "repository: $repository")
    }
  }
}