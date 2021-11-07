package com.takusemba.jethub.repo

import android.util.Base64
import android.widget.TextView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.takusemba.jethub.base.ui.component.BackArrowIconButton
import com.takusemba.jethub.base.ui.component.ProgressView
import com.takusemba.jethub.base.ui.component.TopBar
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.model.Owner
import com.takusemba.jethub.model.ReadMe
import com.takusemba.jethub.model.Repo
import io.noties.markwon.Markwon
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.image.coil.CoilImagesPlugin
import java.nio.charset.StandardCharsets

@Composable
fun RepoScreen(
  repoViewModel: RepoViewModel,
  navigationViewModel: NavigationViewModel
) {
  val uiState by repoViewModel.uiState.collectAsState()

  val scrollState = rememberScrollState()

  Scaffold(
    topBar = {
      RepoTopBar(
        uiState = uiState,
        scrollState = scrollState,
        onBackPressed = { navigationViewModel.popBackStack() },
      )
    },
    content = { paddingValues ->
      RepoBody(
        modifier = Modifier.padding(paddingValues),
        uiState = uiState,
        scrollState = scrollState,
        onOwnerClicked = { owner -> navigationViewModel.openDeveloper(owner.login) }
      )
    }
  )

  if (uiState.isLoading) {
    ProgressView()
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RepoTopBar(
  uiState: RepoUiState,
  scrollState: ScrollState,
  onBackPressed: () -> Unit,
) {
  TopBar(
    title = {
      AnimatedVisibility(
        visible = 200 < scrollState.value,
        enter = fadeIn(),
        exit = fadeOut()
      ) {
        Column(
          verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
          Text(
            text = uiState.repo.name,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
          )
          Text(
            text = uiState.repo.owner.login,
            style = MaterialTheme.typography.subtitle2,
          )
        }
      }
    },
    navigationIcon = { BackArrowIconButton(onBackPressed = onBackPressed) },
    elevation = if (scrollState.value == 0) 0.dp else AppBarDefaults.TopAppBarElevation,
  )
}

@Composable
fun RepoBody(
  modifier: Modifier,
  uiState: RepoUiState,
  scrollState: ScrollState,
  onOwnerClicked: (owner: Owner) -> Unit,
) {
  Column(modifier = modifier.verticalScroll(scrollState)) {
    RepoOverview(
      modifier = Modifier.fillMaxWidth(),
      repo = uiState.repo,
      onOwnerClicked = onOwnerClicked
    )
    RepoReadMe(
      modifier = Modifier.fillMaxWidth(),
      readMe = uiState.readMe,
    )
  }
}

@Composable
fun RepoOverview(
  modifier: Modifier,
  repo: Repo,
  onOwnerClicked: (owner: Owner) -> Unit,
) {
  Column(modifier = modifier) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
        .clickable { onOwnerClicked(repo.owner) },
      verticalAlignment = Alignment.CenterVertically,
    ) {
      if (repo.owner.avatarUrl.isNotEmpty()) {
        Image(
          modifier = Modifier
            .padding(start = 8.dp)
            .padding(vertical = 8.dp)
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
      modifier = Modifier.padding(horizontal = 8.dp),
      style = MaterialTheme.typography.h5,
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
fun RepoReadMe(
  modifier: Modifier,
  readMe: ReadMe,
) {
  Column(modifier = modifier) {
    Text(
      modifier = Modifier
        .padding(horizontal = 16.dp)
        .padding(top = 16.dp),
      text = "ReadMe",
      style = MaterialTheme.typography.h6,
      fontWeight = FontWeight.Bold,
    )
    AndroidView(
      modifier = Modifier
        .padding(horizontal = 16.dp)
        .padding(top = 16.dp, bottom = 16.dp),
      factory = { context ->
        TextView(context)
      },
      update = { textView ->
        Markwon.builder(textView.context)
          .usePlugins(
            listOf(
              CoilImagesPlugin.create(textView.context),
              TablePlugin.create(textView.context),
            )
          )
          .build()
          .setMarkdown(
            textView,
            String(Base64.decode(readMe.content, Base64.DEFAULT), StandardCharsets.UTF_8)
          )
      }
    )
  }
}
