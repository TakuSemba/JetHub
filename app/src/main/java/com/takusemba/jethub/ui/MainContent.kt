package com.takusemba.jethub.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.takusemba.jethub.R

@Composable
fun MainContent() {
  val navController = rememberNavController()
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination

  Scaffold(
    bottomBar = {
      MainBottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        currentDestination = currentDestination,
        onNavigationSelected = { screen ->
          navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
            }
            launchSingleTop = true
            restoreState = true
          }
        }
      )
    },
  ) { paddingValues ->
    AppNavigation(Modifier.padding(paddingValues), navController)
  }
}

@Composable
fun MainBottomNavigation(
  modifier: Modifier,
  currentDestination: NavDestination?,
  onNavigationSelected: (Screen) -> Unit
) {
  BottomNavigation(
    modifier = modifier,
    backgroundColor = MaterialTheme.colors.surface,
    contentColor = contentColorFor(MaterialTheme.colors.surface),
  ) {
    BottomNavigationItem(
      icon = { Icon(painterResource(R.drawable.ic_feed), null) },
      label = { Text(stringResource(id = R.string.feed)) },
      selected = currentDestination?.hierarchy?.any { it.route == Screen.Feed.route } == true,
      onClick = { onNavigationSelected(Screen.Feed) },
      selectedContentColor = MaterialTheme.colors.primary,
      unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
    )

    BottomNavigationItem(
      icon = { Icon(painterResource(R.drawable.ic_search), null) },
      label = { Text(stringResource(id = R.string.search)) },
      selected = currentDestination?.hierarchy?.any { it.route == Screen.Search.route } == true,
      onClick = { onNavigationSelected(Screen.Search) },
      selectedContentColor = MaterialTheme.colors.primary,
      unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
    )

    BottomNavigationItem(
      icon = { Icon(painterResource(R.drawable.ic_pin), null) },
      label = { Text(stringResource(id = R.string.pin)) },
      selected = currentDestination?.hierarchy?.any { it.route == Screen.Pin.route } == true,
      onClick = { onNavigationSelected(Screen.Pin) },
      selectedContentColor = MaterialTheme.colors.primary,
      unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
    )
  }
}
