package com.takusemba.jethub.feed

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.takusemba.jethub.base.model.ColoredLanguage
import com.takusemba.jethub.base.ui.LocalActivity
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
fun FeedScreen(
  feedViewModel: FeedViewModel = hiltViewModel(),
  systemViewModel: SystemViewModel = viewModel(LocalActivity.current),
  userViewModel: UserViewModel = viewModel(LocalActivity.current),
  navigationViewModel: NavigationViewModel = viewModel(LocalActivity.current),
) {

  val uiState by feedViewModel.uiState.collectAsLifecycleAwareState()

  val context = LocalContext.current

  Scaffold(
    topBar = {
      FeedTopBar(
        onNightModePressed = { systemViewModel.toggleNightMode() },
      )
    },
    content = { paddingValues ->
      FeedBody(
        modifier = Modifier.padding(paddingValues),
        uiState = uiState,
        onRepoClicked = { repo -> navigationViewModel.openRepo(repo.owner.login, repo.name) },
        onRepoLongClicked = { repo ->
          if (userViewModel.isPinned(repo)) {
            userViewModel.unpin(repo)
            Toast.makeText(context, R.string.unpinned_repository, Toast.LENGTH_SHORT).show()
          } else {
            userViewModel.pin(repo)
            Toast.makeText(context, R.string.pinned_repository, Toast.LENGTH_SHORT).show()
          }
        },
      )
    }
  )

  if (uiState.isLoading) {
    ProgressView()
  }
}

@Composable
fun FeedTopBar(
  onNightModePressed: () -> Unit,
) {
  TopBar(
    title = { Text(text = stringResource(id = R.string.feed)) },
    actions = { NightModeIconButton(onPressed = onNightModePressed) },
    elevation = 0.dp,
  )
}

@Composable
fun FeedBody(
  modifier: Modifier,
  uiState: FeedUiState,
  onRepoClicked: (repo: Repo) -> Unit,
  onRepoLongClicked: (repo: Repo) -> Unit,
) {
  val languages = ColoredLanguage.POPULAR_LANGUAGES
  var selectedLanguage by remember { mutableStateOf(languages.first()) }
  Column(modifier = modifier) {
    FeedTabs(
      modifier = Modifier.fillMaxWidth(),
      languages = languages,
      selectedLanguage = selectedLanguage,
      onSelectedLanguageChanged = { language -> selectedLanguage = language }
    )
    FeedRepoItems(
      modifier = Modifier.fillMaxWidth(),
      language = selectedLanguage,
      uiState = uiState,
      onRepoClicked = onRepoClicked,
      onRepoLongClicked = onRepoLongClicked,
    )
  }
}

@Composable
fun FeedTabs(
  modifier: Modifier,
  languages: List<ColoredLanguage>,
  selectedLanguage: ColoredLanguage,
  onSelectedLanguageChanged: (language: ColoredLanguage) -> Unit,
) {
  ScrollableTabRow(
    modifier = modifier.shadow(AppBarDefaults.TopAppBarElevation),
    selectedTabIndex = languages.indexOf(selectedLanguage),
    edgePadding = 0.dp,
    backgroundColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.primary,
  ) {
    for (language in languages) {
      Tab(
        selected = selectedLanguage == language,
        onClick = { onSelectedLanguageChanged(language) },
        text = { Text(text = language.title) },
      )
    }
  }
}

@Composable
fun FeedRepoItems(
  modifier: Modifier,
  language: ColoredLanguage,
  uiState: FeedUiState,
  onRepoClicked: (repo: Repo) -> Unit,
  onRepoLongClicked: (repo: Repo) -> Unit,
) {
  LazyColumn(modifier = modifier) {
    val repos = uiState.hotRepos[language.title] ?: emptyList()
    for (repo in repos) {
      item {
        RepoCell(
          modifier = Modifier.fillMaxWidth(),
          repo = repo,
          onClicked = onRepoClicked,
          onLongClicked = onRepoLongClicked,
        )
        Divider()
      }
    }
  }
}
