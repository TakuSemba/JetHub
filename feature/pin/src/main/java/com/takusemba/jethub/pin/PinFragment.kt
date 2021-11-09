package com.takusemba.jethub.pin

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.takusemba.jethub.base.ui.theme.JethubTheme
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PinFragment : Fragment(R.layout.fragment_pin) {

  companion object {

    fun newInstance() = PinFragment()
  }

  @Inject lateinit var recycledViewPool: RecyclerView.RecycledViewPool

  private val navigationViewModel: NavigationViewModel by activityViewModels()
  private val systemViewModel: SystemViewModel by activityViewModels()
  private val userViewModel: UserViewModel by activityViewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.findViewById<ComposeView>(R.id.compose_view).setContent {
      JethubTheme(systemViewModel.isNightMode()) {
        PinScreen(userViewModel, systemViewModel, navigationViewModel)
      }
    }
  }
}
