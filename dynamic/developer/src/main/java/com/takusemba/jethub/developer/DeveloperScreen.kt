package com.takusemba.jethub.developer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.model.Developer

@Composable
fun DeveloperScreen(
  developerViewModel: DeveloperViewModel,
  navigationViewModel: NavigationViewModel
) {
  val uiState by developerViewModel.uiState.collectAsState()

  Scaffold(
    topBar = {
      DeveloperTopBar(
        onBackPressed = { navigationViewModel.popBackStack() }
      )
    },
    content = { paddingValues ->
      DeveloperBody(
        modifier = Modifier.padding(paddingValues),
        uiState = uiState
      )
    }
  )
}

@Composable
fun DeveloperTopBar(onBackPressed: () -> Unit) {
  TopAppBar(
    title = {},
    navigationIcon = {
      IconButton(onClick = onBackPressed) {
        Icon(
          painter = painterResource(R.drawable.ic_back),
          contentDescription = null
        )
      }
    },
    backgroundColor = MaterialTheme.colors.surface,
    elevation = 0.dp,
  )
}

@Composable
fun DeveloperBody(
  modifier: Modifier,
  uiState: DeveloperUiState
) {
  Column(
    modifier = modifier.verticalScroll(rememberScrollState())
  ) {
    DeveloperHeader(
      modifier = Modifier.fillMaxWidth(),
      developer = uiState.developer
    )
    DeveloperReadMe()
  }
}

@Composable
fun DeveloperHeader(
  modifier: Modifier,
  developer: Developer
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center
  ) {
    Row {
      if (developer.avatarUrl.isNotEmpty()) {
        Image(
          modifier = Modifier
            .padding(start = 16.dp)
            .padding(vertical = 8.dp)
            .size(64.dp),
          painter = rememberImagePainter(
            data = developer.avatarUrl,
            builder = {
              transformations(RoundedCornersTransformation(16f))
            }
          ),
          contentDescription = null
        )
      }
      Column(
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          modifier = Modifier
            .padding(top = 8.dp, bottom = 2.dp)
            .padding(horizontal = 16.dp),
          text = developer.name,
          style = MaterialTheme.typography.h5,
        )
        Text(
          modifier = Modifier
            .padding(top = 2.dp, bottom = 8.dp)
            .padding(horizontal = 16.dp),
          text = developer.login,
          style = MaterialTheme.typography.subtitle1,
        )
      }
    }
    if (developer.bio.isNotEmpty()) {
      Text(
        text = developer.bio,
        modifier = Modifier
          .padding(top = 8.dp)
          .padding(horizontal = 16.dp),
        style = MaterialTheme.typography.body1,
      )
    }
    if (developer.company.isNotEmpty()) {
      Row(
        modifier = Modifier
          .padding(top = 4.dp)
          .padding(horizontal = 16.dp)
      ) {
        Icon(
          modifier = Modifier.size(size = 24.dp),
          imageVector = Icons.Default.Business,
          contentDescription = null
        )
        Text(
          modifier = Modifier.padding(start = 4.dp),
          text = developer.company,
          style = MaterialTheme.typography.body1,
          fontWeight = FontWeight.Bold,
        )
      }
    }
    if (developer.location.isNotEmpty()) {
      Text(
        text = developer.location,
        modifier = Modifier
          .padding(top = 4.dp)
          .padding(horizontal = 16.dp),
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Bold,
      )
    }
    if (developer.blog.isNotEmpty()) {
      Text(
        text = developer.blog,
        modifier = Modifier
          .padding(top = 4.dp)
          .padding(horizontal = 16.dp),
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Bold,
      )
    }
    if (developer.email.isNotEmpty()) {
      Text(
        text = developer.email,
        modifier = Modifier
          .padding(top = 4.dp)
          .padding(horizontal = 16.dp),
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Bold,
      )
    }
    Row(modifier = Modifier.padding(top = 16.dp)) {
      Text(
        modifier = Modifier.padding(start = 16.dp),
        text = "${developer.followersCount} followers",
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Bold,
      )
      Text(
        text = "${developer.followingCount} following",
        modifier = Modifier.padding(start = 8.dp, end = 16.dp),
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Bold,
      )
    }
  }
}

@Composable
fun DeveloperReadMe() {
  Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
    Text(
      text = "show more details here.",
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.h5,
      color = MaterialTheme.colors.onSurface
    )
  }
}
