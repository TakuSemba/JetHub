package com.takusemba.jethub.repo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.takusemba.jethub.base.ui.component.BackArrowIconButton
import com.takusemba.jethub.base.ui.component.ProgressView
import com.takusemba.jethub.base.ui.component.TopBar
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.model.Repo

@Composable
fun RepoScreen(
  repoViewModel: RepoViewModel,
  navigationViewModel: NavigationViewModel
) {
  val uiState by repoViewModel.uiState.collectAsState()

  Scaffold(
    topBar = { RepoTopBar(onBackPressed = { navigationViewModel.popBackStack() }) },
    content = { paddingValues ->
      RepoBody(
        modifier = Modifier.padding(paddingValues),
        uiState = uiState,
      )
    }
  )

  if (uiState.isLoading) {
    ProgressView()
  }
}

@Composable
fun RepoTopBar(onBackPressed: () -> Unit) {
  TopBar(
    navigationIcon = { BackArrowIconButton(onBackPressed = onBackPressed) },
    elevation = 0.dp,
  )
}

@Composable
fun RepoBody(
  modifier: Modifier,
  uiState: RepoUiState,
) {
  Column(modifier = modifier.verticalScroll(rememberScrollState())) {
    RepoOverview(
      modifier = Modifier.fillMaxWidth(),
      repo = uiState.repo,
    )
  }
}

@Composable
fun RepoOverview(
  modifier: Modifier,
  repo: Repo,
) {
  Column(modifier = modifier) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      if (repo.owner.avatarUrl.isNotEmpty()) {
        Image(
          modifier = Modifier
            .padding(start = 16.dp)
            .size(20.dp),
          painter = rememberImagePainter(
            data = repo.owner.avatarUrl,
            builder = {
              transformations(RoundedCornersTransformation(16.0f))
            }
          ),
          contentDescription = null
        )
      }
      Text(
        text = repo.owner.login,
        modifier = Modifier.padding(start = 8.dp, end = 16.dp),
        style = MaterialTheme.typography.subtitle2
      )
    }
    Text(
      text = repo.name,
      modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
      style = MaterialTheme.typography.h4,
      fontWeight = FontWeight.Bold,
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
        modifier = Modifier.size(size = 18.dp),
        imageVector = Icons.Default.Star,
        contentDescription = null,
        tint = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
      )
      Text(
        text = "${repo.starsCount} stars",
        modifier = Modifier.padding(start = 8.dp, end = 16.dp)
      )
      Icon(
        modifier = Modifier.size(size = 18.dp),
        imageVector = Icons.Default.AltRoute,
        contentDescription = null,
        tint = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
      )
      Text(text = "${repo.forksCount} forks", modifier = Modifier.padding(start = 8.dp))
    }
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
