package com.takusemba.jethub.search

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
  searchViewModel: SearchViewModel = hiltViewModel(),
  userViewModel: UserViewModel = viewModel(LocalActivity.current),
  systemViewModel: SystemViewModel = viewModel(LocalActivity.current),
  navigationViewModel: NavigationViewModel = viewModel(LocalActivity.current),
) {
  val uiState by searchViewModel.uiState.collectAsLifecycleAwareState()

  val context = LocalContext.current
  val keyboardController = LocalSoftwareKeyboardController.current
  val listState = rememberLazyListState()

  if (listState.isScrollInProgress) {
    keyboardController?.hide()
  }

  Scaffold(
    topBar = {
      SearchTopBar(
        onNightModePressed = { systemViewModel.toggleNightMode() },
        listState = listState,
      )
    },
    content = { paddingValues ->
      SearchBody(
        modifier = Modifier.padding(paddingValues),
        uiState = uiState,
        listState = listState,
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
        onQueryChanged = { query -> searchViewModel.search(query) },
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
  listState: LazyListState,
) {
  TopBar(
    title = { Text(text = stringResource(id = R.string.search)) },
    actions = { NightModeIconButton(onPressed = onNightModePressed) },
    elevation = if (
      listState.firstVisibleItemIndex == 0 &&
      listState.firstVisibleItemScrollOffset < 25
    ) {
      0.dp
    } else {
      AppBarDefaults.TopAppBarElevation
    },
  )
}

@Composable
fun SearchBody(
  modifier: Modifier,
  uiState: SearchUiState,
  listState: LazyListState,
  onRepoClicked: (repo: Repo) -> Unit,
  onRepoLongClicked: (repo: Repo) -> Unit,
  onQueryChanged: (query: String) -> Unit,
) {
  Box(modifier = modifier) {
    if (uiState.repos.isEmpty() && !uiState.isLoading) {
      SearchEmptyLayout(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
      )
    }
    SearchRepoItemsWithSearchBar(
      modifier = Modifier.fillMaxWidth(),
      uiState = uiState,
      listState = listState,
      onRepoClicked = onRepoClicked,
      onRepoLongClicked = onRepoLongClicked,
      onQueryChanged = onQueryChanged,
    )
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchRepoItemsWithSearchBar(
  modifier: Modifier,
  uiState: SearchUiState,
  listState: LazyListState,
  onRepoClicked: (repo: Repo) -> Unit,
  onRepoLongClicked: (repo: Repo) -> Unit,
  onQueryChanged: (query: String) -> Unit,
) {
  LazyColumn(
    modifier = modifier,
    state = listState,
  ) {
    stickyHeader {
      OutlinedTextField(
        modifier = Modifier
          .padding(vertical = 16.dp, horizontal = 16.dp)
          .fillMaxWidth()
          .background(MaterialTheme.colors.background),
        value = uiState.query,
        leadingIcon = {
          Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = null
          )
        },
        label = { Text(text = stringResource(id = R.string.hint_search)) },
        onValueChange = onQueryChanged,
        singleLine = true,
      )
    }
    for (pinnedRepo in uiState.repos) {
      item {
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
}

@Composable
fun SearchEmptyLayout(
  modifier: Modifier,
  uiState: SearchUiState,
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
      text = stringResource(id = R.string.empty_search_repositories_description, uiState.query),
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.body2,
    )
  }
}
