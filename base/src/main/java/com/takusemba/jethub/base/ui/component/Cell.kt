package com.takusemba.jethub.base.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.takusemba.jethub.base.R
import com.takusemba.jethub.base.model.ColoredLanguage
import com.takusemba.jethub.model.Owner
import com.takusemba.jethub.model.Repo

@Composable
fun RepoCell(
  modifier: Modifier,
  repo: Repo,
  onClicked: (repo: Repo) -> Unit,
) {
  Column(modifier = modifier.clickable { onClicked(repo) }) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Image(
        modifier = Modifier
          .padding(start = 16.dp)
          .padding(vertical = 16.dp)
          .size(24.dp),
        painter = rememberImagePainter(
          data = repo.owner.avatarUrl,
          builder = {
            transformations(RoundedCornersTransformation(16f))
          }
        ),
        contentDescription = null,
      )
      Text(
        modifier = Modifier
          .padding(start = 8.dp, end = 16.dp)
          .padding(vertical = 16.dp),
        text = repo.owner.login,
        style = MaterialTheme.typography.subtitle2,
      )
    }
    Text(
      modifier = Modifier.padding(horizontal = 16.dp),
      text = repo.name,
      style = MaterialTheme.typography.h6,
    )
    if (repo.description.isNotBlank()) {
      Text(
        modifier = Modifier
          .padding(top = 8.dp)
          .padding(horizontal = 16.dp),
        text = repo.description,
        style = MaterialTheme.typography.body1,
      )
    }
    Row(
      modifier = Modifier
        .padding(bottom = 8.dp)
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      val language = ColoredLanguage.of(repo.language)
      Image(
        modifier = Modifier
          .padding(start = 16.dp)
          .padding(vertical = 12.dp)
          .size(8.dp),
        painter = painterResource(language.icon),
        contentDescription = null,
      )
      Text(
        modifier = Modifier
          .padding(start = 4.dp)
          .padding(vertical = 8.dp),
        text = language.title,
        style = MaterialTheme.typography.body2,
      )
      Image(
        modifier = Modifier
          .padding(start = 8.dp)
          .padding(vertical = 8.dp)
          .size(12.dp),
        painter = painterResource(R.drawable.ic_star),
        contentDescription = null,
      )
      Text(
        modifier = Modifier
          .padding(start = 4.dp)
          .padding(vertical = 8.dp),
        text = repo.starsCount.toString(),
        style = MaterialTheme.typography.body2,
      )
    }
  }
}

@Preview
@Composable
fun RepoCellPreview() {
  RepoCell(
    modifier = Modifier.fillMaxWidth(),
    repo = Repo.createRepo(
      id = 1,
      owner = Owner.createOwner(
        id = 1,
        login = "Owner Name",
        avatarUrl = "https://avatars3.githubusercontent.com/u/13956869",
      ),
      name = "Repo Name",
      description = "Repo Description",
      language = "Kotlin"
    ),
    onClicked = {}
  )
}
