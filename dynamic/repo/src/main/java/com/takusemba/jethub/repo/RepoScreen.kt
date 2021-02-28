package com.takusemba.jethub.repo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.compose.JethubTheme
import com.takusemba.jethub.database.dao.RepositoryDao_Impl
import com.takusemba.jethub.model.Repo
import com.takusemba.jethub.repository.RepoRepository
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun RepoScreen(
  repoViewModel: RepoViewModel,
  navigationViewModel: NavigationViewModel
) {
  val repo = repoViewModel.repository.observeAsState(Repo.EMPTY)
  Scaffold(
    topBar = { RepoTopBar(navigationViewModel) },
    content = {
      LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        item {
          Header(repo.value)
          Body(
            developerButtonClicked = { navigationViewModel.openDeveloper(repo.value.owner.login) }
          )
        }
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
        Icon(
          painter = painterResource(R.drawable.ic_back),
          contentDescription = "back"
        )
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
          modifier = Modifier.size(16.dp),
          contentDescription = "avator"
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
fun Body(developerButtonClicked: () -> Unit) {
  Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
    Text(
      text = "show README.md here.",
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.h5,
      color = MaterialTheme.colors.onSurface
    )
    Button(
      onClick = developerButtonClicked,
      modifier = Modifier.padding(top = 16.dp)
    ) {
      Text(
        text = "Go to Developer Fragment",
        modifier = Modifier.padding(8.dp)
      )
    }
  }
}

@Preview
@Composable
fun PreviewRepoScreenBody(){
  JethubTheme(darkTheme = false) {
    Body{}
  }
}

