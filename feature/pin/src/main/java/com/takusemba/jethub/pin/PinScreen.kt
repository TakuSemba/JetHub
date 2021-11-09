package com.takusemba.jethub.pin

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.takusemba.jethub.base.ui.component.NightModeIconButton
import com.takusemba.jethub.base.ui.component.RepoCell
import com.takusemba.jethub.base.ui.component.TopBar
import com.takusemba.jethub.base.util.collectAsLifecycleAwareState
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.base.viewmodel.UserUiState
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.model.Repo

@Composable
fun PinScreen(
  userViewModel: UserViewModel,
  systemViewModel: SystemViewModel,
  navigationViewModel: NavigationViewModel,
) {
  val uiState by userViewModel.uiState.collectAsLifecycleAwareState()

  val scrollState = rememberScrollState()

  Scaffold(
    topBar = {
      PinTopBar(
        onNightModePressed = { systemViewModel.toggleNightMode() },
        scrollState = scrollState,
      )
    },
    content = { paddingValues ->
      PinBody(
        modifier = Modifier.padding(paddingValues),
        uiState = uiState,
        scrollState = scrollState,
        onRepoClicked = { repo -> navigationViewModel.openRepo(repo.owner.login, repo.name) }
      )
    }
  )
}

@Composable
fun PinTopBar(
  onNightModePressed: () -> Unit,
  scrollState: ScrollState,
) {
  TopBar(
    title = { Text(text = stringResource(id = R.string.pin)) },
    actions = { NightModeIconButton(onPressed = onNightModePressed) },
    elevation = if (scrollState.value == 0) 0.dp else AppBarDefaults.TopAppBarElevation,
  )
}

@Composable
fun PinBody(
  modifier: Modifier,
  uiState: UserUiState,
  scrollState: ScrollState,
  onRepoClicked: (repo: Repo) -> Unit,
) {
  Column(
    modifier = modifier.verticalScroll(scrollState)
  ) {
    for (pinnedRepo in uiState.pinnedRepos) {
      RepoCell(
        modifier = Modifier.fillMaxWidth(),
        repo = pinnedRepo,
        onClicked = onRepoClicked,
      )
      Divider()
    }
  }
}
