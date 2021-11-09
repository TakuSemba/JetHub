package com.takusemba.jethub.base.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp

@Composable
fun TopBar(
  modifier: Modifier = Modifier,
  title: @Composable () -> Unit = {},
  navigationIcon: @Composable (() -> Unit)? = null,
  actions: @Composable RowScope.() -> Unit = {},
  backgroundColor: Color = MaterialTheme.colors.surface,
  contentColor: Color = contentColorFor(backgroundColor),
  elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
  TopAppBar(
    title = title,
    modifier = modifier,
    navigationIcon = navigationIcon,
    actions = actions,
    backgroundColor = backgroundColor,
    contentColor = contentColor,
    elevation = elevation
  )
}

@Composable
fun TopBarTitle(@StringRes title: Int) {
  TopBarTitle(title = stringResource(id = title))
}

@Composable
fun TopBarTitle(title: String) {
  Text(
    text = title,
    textAlign = TextAlign.Start,
    color = MaterialTheme.colors.onSurface,
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
  )
}

@Composable
fun BackArrowIconButton(onPressed: () -> Unit) {
  IconButton(onClick = { onPressed() }) {
    Icon(
      imageVector = Icons.Default.ArrowBack,
      contentDescription = null,
      tint = MaterialTheme.colors.onSurface
    )
  }
}

@Composable
fun CloseIconButton(onPressed: () -> Unit) {
  IconButton(onClick = { onPressed() }) {
    Icon(
      imageVector = Icons.Default.Close,
      contentDescription = null,
      tint = MaterialTheme.colors.onSurface
    )
  }
}

@Composable
fun NightModeIconButton(onPressed: () -> Unit) {
  IconButton(onClick = { onPressed() }) {
    Icon(
      imageVector = Icons.Default.ModeNight,
      contentDescription = null,
      tint = MaterialTheme.colors.onSurface
    )
  }
}
