package com.takusemba.jethub.developer

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.padding
import androidx.ui.layout.preferredSize
import androidx.ui.livedata.observeAsState
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.res.vectorResource
import androidx.ui.text.style.TextAlign
import androidx.ui.unit.dp
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.model.Developer
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DeveloperScreen(
  developerViewModel: DeveloperViewModel,
  navigationViewModel: NavigationViewModel
) {
  val developer = developerViewModel.developer.observeAsState(Developer.EMPTY)
  Scaffold(
    topBar = { DeveloperTopBar(navigationViewModel) },
    bodyContent = { innerPadding ->
      VerticalScroller(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Header(developer.value)
        Body()
      }
    }
  )
}

@Composable
fun DeveloperTopBar(navigationViewModel: NavigationViewModel) {
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
fun Header(developer: Developer) {
  Column(modifier = Modifier.padding(top = 16.dp)) {
    Row {
      if (developer.avatarUrl.isNotEmpty()) {
        CoilImage(
          data = developer.avatarUrl,
          modifier = Modifier.preferredSize(64.dp)
        )
      }
      Column(modifier = Modifier.padding(start = 16.dp)) {
        Text(
          text = developer.name,
          style = MaterialTheme.typography.h5,
          color = MaterialTheme.colors.onSurface
        )
        Text(
          text = developer.login,
          style = MaterialTheme.typography.subtitle1,
          color = MaterialTheme.colors.onSurface
        )
      }
    }
    if (developer.bio.isNotEmpty()) {
      Text(
        text = developer.bio,
        modifier = Modifier.padding(top = 16.dp),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface
      )
    }
    if (developer.company.isNotEmpty()) {
      Text(
        text = developer.company,
        modifier = Modifier.padding(top = 4.dp),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface
      )
    }
    if (developer.location.isNotEmpty()) {
      Text(
        text = developer.location,
        modifier = Modifier.padding(top = 4.dp),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface
      )
    }
    if (developer.blog.isNotEmpty()) {
      Text(
        text = developer.blog,
        modifier = Modifier.padding(top = 4.dp),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface
      )
    }
    if (developer.email.isNotEmpty()) {
      Text(
        text = developer.email,
        modifier = Modifier.padding(top = 4.dp),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface
      )
    }
    Row(modifier = Modifier.padding(top = 16.dp)) {
      Text(
        text = "${developer.followersCount} followers",
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface
      )
      Text(
        text = "${developer.followingCount} following",
        modifier = Modifier.padding(start = 8.dp),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onSurface
      )
    }
  }
}

@Composable
fun Body() {
  Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
    Text(
      text = "show more details here.",
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.h5,
      color = MaterialTheme.colors.onSurface
    )
  }
}