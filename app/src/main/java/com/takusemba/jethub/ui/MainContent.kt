package com.takusemba.jethub.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
  NavigationBar(
    modifier = modifier,
  ) {
    NavigationBarItem(
      icon = { Icon(painterResource(R.drawable.ic_feed), null) },
      label = { Text(stringResource(id = R.string.feed)) },
      selected = currentDestination?.hierarchy?.any { it.route == Screen.Feed.route } == true,
      onClick = { onNavigationSelected(Screen.Feed) },
    )

    NavigationBarItem(
      icon = { Icon(painterResource(R.drawable.ic_search), null) },
      label = { Text(stringResource(id = R.string.search)) },
      selected = currentDestination?.hierarchy?.any { it.route == Screen.Search.route } == true,
      onClick = { onNavigationSelected(Screen.Search) },
    )

    NavigationBarItem(
      icon = { Icon(painterResource(R.drawable.ic_pin), null) },
      label = { Text(stringResource(id = R.string.pin)) },
      selected = currentDestination?.hierarchy?.any { it.route == Screen.Pin.route } == true,
      onClick = { onNavigationSelected(Screen.Pin) },
    )
  }
}
