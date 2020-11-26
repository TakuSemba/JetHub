package com.takusemba.jethub.repo

import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.model.Repo
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun RepoScreen(
  repoViewModel: RepoViewModel,
  navigationViewModel: NavigationViewModel
) {
  val repo = repoViewModel.repository.observeAsState(Repo.EMPTY)
  Scaffold(
    topBar = { RepoTopBar(navigationViewModel) },
    bodyContent = { innerPadding ->
      ScrollableColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Header(repo.value)
        Body(
          repo = repo.value,
          navigationViewModel = navigationViewModel
        )
      }
    }
  )
}

@Composable
fun RepoTopBar(navigationViewModel: NavigationViewModel) {
  TopAppBar(
    title = {},
    navigationIcon = {
      IconButton(onClick = { navigationViewModel.popBackStack() }) {
        Icon(vectorResource(R.drawable.ic_back))
      }
    },
    backgroundColor = MaterialTheme.colors.surface
  )
}

@Composable
fun Header(repo: Repo) {
  Column(modifier = Modifier.padding(top = 16.dp)) {
    Row {
      if (repo.owner.avatarUrl.isNotEmpty()) {
        CoilImage(
          data = repo.owner.avatarUrl,
          modifier = Modifier.preferredSize(16.dp)
        )
      }
      Text(
        text = repo.owner.login,
        modifier = Modifier.padding(start = 8.dp),
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.onSurface
      )
    }
    Text(
      text = repo.name,
      modifier = Modifier.padding(top = 8.dp),
      style = MaterialTheme.typography.h6,
      color = MaterialTheme.colors.onSurface
    )
    Text(
      text = repo.description,
      modifier = Modifier.padding(top = 4.dp),
      style = MaterialTheme.typography.body1,
      color = MaterialTheme.colors.onSurface
    )
    Row(modifier = Modifier.padding(top = 8.dp)) {
      Text(
        text = "${repo.starsCount} stars",
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface
      )
      Text(
        text = "${repo.forksCount} forks",
        modifier = Modifier.padding(start = 8.dp),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface
      )
    }
  }
}

@Composable
fun Body(
  repo: Repo,
  navigationViewModel: NavigationViewModel
) {
  Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
    Text(
      text = "show README.md here.",
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.h5,
      color = MaterialTheme.colors.onSurface
    )
    Button(
      onClick = { navigationViewModel.openDeveloper(repo.owner.login) },
      modifier = Modifier.padding(top = 16.dp)
    ) {
      Text(
        text = "Go to Developer Fragment",
        modifier = Modifier.padding(8.dp)
      )
    }
  }
}
