package com.takusemba.jethub.developer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.RssFeed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
    DeveloperProfile(
      modifier = Modifier.fillMaxWidth(),
      developer = uiState.developer
    )
    DeveloperReadMe(
      modifier = Modifier.fillMaxWidth(),
    )
  }
}

@Composable
fun DeveloperProfile(
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
          fontWeight = FontWeight.Bold,
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
      DeveloperProfileIconItem(
        modifier = Modifier
          .padding(top = 8.dp)
          .padding(horizontal = 16.dp),
        icon = Icons.Default.Business,
        text = developer.company,
      )
    }
    if (developer.location.isNotEmpty()) {
      DeveloperProfileIconItem(
        modifier = Modifier
          .padding(top = 8.dp)
          .padding(horizontal = 16.dp),
        icon = Icons.Default.Place,
        text = developer.location,
      )
    }
    if (developer.blog.isNotEmpty()) {
      DeveloperProfileIconItem(
        modifier = Modifier
          .padding(top = 8.dp)
          .padding(horizontal = 16.dp),
        icon = Icons.Default.RssFeed,
        text = developer.blog,
      )
    }
    if (developer.email.isNotEmpty()) {
      DeveloperProfileIconItem(
        modifier = Modifier
          .padding(top = 8.dp)
          .padding(horizontal = 16.dp),
        icon = Icons.Default.Email,
        text = developer.email,
      )
    }
    DeveloperProfileIconItem(
      modifier = Modifier
        .padding(top = 8.dp)
        .padding(horizontal = 16.dp),
      icon = Icons.Default.Person,
      text = "${developer.followersCount} followers ${developer.followingCount} following",
    )
  }
}

@Composable
fun DeveloperProfileIconItem(
  modifier: Modifier,
  icon: ImageVector,
  text: String,
) {
  Row(modifier = modifier) {
    Icon(
      modifier = Modifier.size(size = 18.dp),
      imageVector = icon,
      contentDescription = null,
      tint = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
    )
    Text(
      modifier = Modifier.padding(start = 4.dp),
      text = text,
      style = MaterialTheme.typography.body2,
      fontWeight = FontWeight.Bold,
    )
  }
}

@Composable
fun DeveloperReadMe(
  modifier: Modifier
) {
  Column(modifier = modifier) {
    Text(
      modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp),
      text = "show more details here.",
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.h5,
      color = MaterialTheme.colors.onSurface
    )
  }
}
