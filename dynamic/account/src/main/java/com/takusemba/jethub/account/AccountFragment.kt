package com.takusemba.jethub.account

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.compose.Composable
import androidx.compose.Recomposer
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.livedata.observeAsState
import com.takusemba.jethub.account.theme.JethubTheme
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.di.AccountModuleDependencies
import com.takusemba.jethub.model.Developer
import dagger.hilt.android.EntryPointAccessors

/**
 * AccountFragment is under development...
 */
class AccountFragment : Fragment(R.layout.fragment_account) {

  private val userViewModel: UserViewModel by activityViewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DaggerAccountComponent.builder()
      .context(requireContext())
      .appDependencies(
        EntryPointAccessors.fromApplication(
          requireContext().applicationContext,
          AccountModuleDependencies::class.java
        )
      )
      .build()
      .inject(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val developer = userViewModel.developer

    (view as ViewGroup).setContent(Recomposer.current()) {
      val developerState = developer.observeAsState()
      JethubTheme {
        Column {
          Text("state: ${developerState.value}")
        }
      }
    }

    userViewModel.developer.observe(viewLifecycleOwner) {
      Log.d("TEST", "developer: $it")
    }

    userViewModel.loadProfile(DEVELOPER_NAME)
  }

  companion object {

    // TODO retrieve name from local.properties later.
    private const val DEVELOPER_NAME = "TakuSemba"
  }
}

@Composable
fun DeveloperProfile(developerLiveData: LiveData<Developer>) {
  val developerState = developerLiveData.observeAsState().value
  Text(text = "$developerState")
}