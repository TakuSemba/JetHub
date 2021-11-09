package com.takusemba.jethub

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.takusemba.jethub.api.di.NetworkModule
import com.takusemba.jethub.database.di.DatabaseModule
import com.takusemba.jethub.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@UninstallModules(NetworkModule::class, DatabaseModule::class)
@RunWith(AndroidJUnit4::class)
class MainTabFragmentTest {

  @get:Rule(order = 0)
  var hiltRule = HiltAndroidRule(this)

  @get:Rule(order = 1)
  var activityRule = activityScenarioRule<MainActivity>()

  @Before
  fun setup() {
    hiltRule.inject()
  }

  @Test
  fun mainTabFragmentTest() {
    onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))

    // default tab
    onView(allOf(withId(R.id.title), withText(R.string.feed))).check(matches(isDisplayed()))

    // search tab
    onView(
      allOf(isDescendantOfA(withId(R.id.bottom_navigation)), withId(R.id.search))
    ).perform(click())
    onView(allOf(withId(R.id.title), withText(R.string.search))).check(matches(isDisplayed()))

    // pin tab
    onView(
      allOf(isDescendantOfA(withId(R.id.bottom_navigation)), withId(R.id.pin))
    ).perform(click())
    onView(allOf(withId(R.id.title), withText(R.string.pin))).check(matches(isDisplayed()))
  }
}