package com.takusemba.jethub.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.takusemba.jethub.feed.FeedScreen
import com.takusemba.jethub.pin.PinScreen
import com.takusemba.jethub.search.SearchScreen

sealed class Screen(
  val route: String
) {
  object Feed : Screen("feed")
  object Search : Screen("search")
  object Pin : Screen("pin")
}

@Composable
fun AppNavigation(
  modifier: Modifier,
  navController: NavHostController,
) {
  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = Screen.Feed.route,
  ) {
    composable(Screen.Feed.route) {
      FeedScreen()
    }
    composable(Screen.Search.route) {
      SearchScreen()
    }
    composable(Screen.Pin.route) {
      PinScreen()
    }
  }
}