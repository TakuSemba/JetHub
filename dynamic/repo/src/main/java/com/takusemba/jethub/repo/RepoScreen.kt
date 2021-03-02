package com.takusemba.jethub.repo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.compose.JethubTheme
import com.takusemba.jethub.model.Owner
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
    content = {
      RepoContent(
        repo = repo.value,
        developerButtonClicked = { navigationViewModel.openDeveloper(repo.value.owner.login) }
      )
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
fun RepoContent(
  repo: Repo,
  developerButtonClicked: () -> Unit
){
  LazyColumn {
    item {
      Developer(owner = repo.owner)
      RepoOverview(repo = repo)
      GoToDeveloperButton(
        developerButtonClicked = developerButtonClicked
      )
    }
  }
}

@Composable
fun Developer(owner: Owner){
  Row(
    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
    horizontalArrangement = Arrangement.Center
  ) {
    if (owner.avatarUrl.isNotEmpty()) {
      CoilImage(
        data = owner.avatarUrl,
        modifier = Modifier.size(20.dp),
        requestBuilder = {
          transformations(RoundedCornersTransformation(18.0f))
        },
        contentDescription = "avator"
      )
    }
    Text(
      text = owner.login,
      modifier = Modifier.padding(start = 8.dp),
      style = MaterialTheme.typography.subtitle2
    )
  }
}

@Composable
fun RepoOverview(repo: Repo){
  Text(
    text = repo.name,
    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    style = MaterialTheme.typography.h4
  )
  Text(
    text = repo.description,
    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    style = MaterialTheme.typography.body1
  )
  Row(
    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Icon(
      painter = painterResource(R.drawable.ic_star_outline),
      modifier = Modifier.size(18.dp),
      contentDescription = "star"
    )
    Text(text = "${repo.starsCount} stars", modifier = Modifier.padding(start = 8.dp, end = 16.dp))
    Icon(
      painter = painterResource(R.drawable.ic_fork_outline),
      modifier = Modifier.size(18.dp),
      contentDescription = "fork"
    )
    Text(text = "${repo.forksCount} forks", modifier = Modifier.padding(start = 8.dp))
  }
}

@Composable
fun GoToDeveloperButton(developerButtonClicked: () -> Unit) {
  Column(modifier = Modifier.padding(16.dp)) {
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
    GoToDeveloperButton {}
  }
}

