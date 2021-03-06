package com.takusemba.jethub.developer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.takusemba.jethub.model.Developer
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DeveloperScreen(
  developerViewModel: DeveloperViewModel,
  navigationViewModel: NavigationViewModel
) {
  val developer = developerViewModel.developer.observeAsState(Developer.EMPTY)
  Scaffold(
    topBar = { DeveloperTopBar(navigationIconClicked = {navigationViewModel.popBackStack()}) },
    content = {
      LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        item {
          Header(developer.value)
          Body()
        }
      }
    }
  )
}

@Composable
fun DeveloperTopBar(navigationIconClicked: () -> Unit) {
  TopAppBar(
    title = {},
    navigationIcon = {
      IconButton(onClick = navigationIconClicked) {
        Icon(painterResource(R.drawable.ic_back), "back")
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
          modifier = Modifier.size(64.dp),
          contentDescription = "avator"
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

@Preview
@Composable
fun PreviewDeveloperScreenBody(){
  JethubTheme(darkTheme = false) {
    Body()
  }
}
