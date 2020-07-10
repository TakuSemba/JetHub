package com.takusemba.jethub.developer

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
import androidx.ui.livedata.observeAsState
import androidx.ui.material.MaterialTheme
import androidx.ui.text.style.TextAlign
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.compose.JethubTheme
import com.takusemba.jethub.di.DeveloperModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class DeveloperFragment : Fragment(R.layout.fragment_developer) {

  @Inject
  lateinit var developerViewModelProviderFactory: DeveloperViewModelProviderFactory

  private val developerViewModel: DeveloperViewModel by viewModels { developerViewModelProviderFactory }
  private val systemViewModel: SystemViewModel by activityViewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DaggerDeveloperComponent.builder()
      .fragment(this)
      .appDependencies(
        EntryPointAccessors.fromApplication(
          requireContext().applicationContext,
          DeveloperModuleDependencies::class.java
        )
      )
      .build()
      .inject(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    (view as ViewGroup).setContent(Recomposer.current()) {
      JethubTheme(systemViewModel.isNightMode()) {
        Column(
          modifier = Modifier.fillMaxWidth().fillMaxHeight(),
          verticalArrangement = Arrangement.Center,
          horizontalGravity = Alignment.CenterHorizontally
        ) {
          Text(
            text = "This screen is under development...",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface
          )
          Box(
            gravity = ContentGravity.Center
          ) {
            Text(
              text = "Developer Fragment",
              textAlign = TextAlign.Center,
              style = MaterialTheme.typography.h4,
              color = MaterialTheme.colors.onSurface
            )
            val developer = developerViewModel.developer.observeAsState()
            Text(
              text = developer.value.toString(),
              textAlign = TextAlign.Center,
              style = MaterialTheme.typography.caption,
              color = MaterialTheme.colors.onSurface
            )
          }
        }
      }
    }

    developerViewModel.developer.observe(viewLifecycleOwner) { developer ->
      Log.d("TEST", "developer $developer")
    }
  }
}
