package com.takusemba.jethub.search

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.takusemba.jethub.base.ui.component.NightModeIconButton
import com.takusemba.jethub.base.ui.component.ProgressView
import com.takusemba.jethub.base.ui.component.RepoCell
import com.takusemba.jethub.base.ui.component.TopBar
import com.takusemba.jethub.base.util.collectAsLifecycleAwareState
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.model.Repo

@Composable
fun SearchScreen(
  searchViewModel: SearchViewModel,
  userViewModel: UserViewModel,
  systemViewModel: SystemViewModel,
  navigationViewModel: NavigationViewModel,
) {
  val uiState by searchViewModel.uiState.collectAsLifecycleAwareState()

  val context = LocalContext.current
  val scrollState = rememberScrollState()

  Scaffold(
    topBar = {
      SearchTopBar(
        onNightModePressed = { systemViewModel.toggleNightMode() },
        scrollState = scrollState,
      )
    },
    content = { paddingValues ->
      SearchBody(
        modifier = Modifier.padding(paddingValues),
        uiState = uiState,
        scrollState = scrollState,
        onRepoClicked = { repo -> navigationViewModel.openRepo(repo.owner.login, repo.name) },
        onRepoLongClicked = { repo ->
          if (userViewModel.isPinned(repo)) {
            userViewModel.unpin(repo)
            Toast.makeText(context, R.string.unpinned_repository, Toast.LENGTH_SHORT).show()
          } else {
            userViewModel.pin(repo)
            Toast.makeText(context, R.string.pinned_repository, Toast.LENGTH_SHORT).show()
          }
        }
      )
    }
  )

  if (uiState.isLoading) {
    ProgressView()
  }
}

@Composable
fun SearchTopBar(
  onNightModePressed: () -> Unit,
  scrollState: ScrollState,
) {
  TopBar(
    title = { Text(text = stringResource(id = R.string.search)) },
    actions = { NightModeIconButton(onPressed = onNightModePressed) },
    elevation = if (scrollState.value == 0) 0.dp else AppBarDefaults.TopAppBarElevation,
  )
}

@Composable
fun SearchBody(
  modifier: Modifier,
  uiState: SearchUiState,
  scrollState: ScrollState,
  onRepoClicked: (repo: Repo) -> Unit,
  onRepoLongClicked: (repo: Repo) -> Unit,
) {
  Box(modifier = modifier) {
    if (uiState.repos.isEmpty()) {
      SearchEmptyLayout(modifier = Modifier.fillMaxSize())
    } else {
      SearchRepoItems(
        modifier = Modifier.fillMaxWidth(),
        uiState = uiState,
        scrollState = scrollState,
        onRepoClicked = onRepoClicked,
        onRepoLongClicked = onRepoLongClicked
      )
    }
  }
}

@Composable
fun SearchRepoItems(
  modifier: Modifier,
  uiState: SearchUiState,
  scrollState: ScrollState,
  onRepoClicked: (repo: Repo) -> Unit,
  onRepoLongClicked: (repo: Repo) -> Unit,
) {
  Column(
    modifier = modifier.verticalScroll(scrollState)
  ) {
    for (pinnedRepo in uiState.repos) {
      RepoCell(
        modifier = Modifier.fillMaxWidth(),
        repo = pinnedRepo,
        onClicked = onRepoClicked,
        onLongClicked = onRepoLongClicked,
      )
      Divider()
    }
  }
}

@Composable
fun SearchEmptyLayout(
  modifier: Modifier,
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Image(
      painter = painterResource(R.drawable.ic_empty_search),
      contentDescription = null,
    )
    Text(
      modifier = Modifier
        .padding(horizontal = 32.dp)
        .padding(top = 16.dp),
      text = stringResource(id = R.string.empty_search_repositories_title),
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.h6,
      fontWeight = FontWeight.Bold,
    )
    Text(
      modifier = Modifier
        .padding(horizontal = 32.dp)
        .padding(top = 16.dp),
      text = stringResource(id = R.string.empty_search_repositories_description),
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.body2,
    )
  }
}
