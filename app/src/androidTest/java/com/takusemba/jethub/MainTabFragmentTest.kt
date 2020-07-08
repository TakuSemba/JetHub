package com.takusemba.jethub

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.takusemba.jethub.ui.activity.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.OkHttpClient
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainTabFragmentTest {

  @Inject
  lateinit var okHttpClient: OkHttpClient

  @get:Rule(order = 0)
  var hiltRule = HiltAndroidRule(this)

  @get:Rule(order = 1)
  var activityRule = activityScenarioRule<MainActivity>()

  @Test
  fun mainTabFragmentTest() {
    hiltRule.inject()
    assertThat(okHttpClient).isNotNull()
  }
}