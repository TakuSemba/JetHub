package com.takusemba.jethub.repo

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.padding
import androidx.ui.layout.preferredSize
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Button
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.res.vectorResource
import androidx.ui.text.style.TextAlign
import androidx.ui.unit.dp
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
      Column(modifier = Modifier.padding(16.dp)) {
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
  Column {
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
  Column(modifier = Modifier.padding(top = 16.dp)) {
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
