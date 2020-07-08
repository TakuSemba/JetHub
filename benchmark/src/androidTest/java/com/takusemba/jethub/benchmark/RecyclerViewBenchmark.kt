package com.takusemba.jethub.benchmark

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams.MATCH_PARENT
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.annotation.UiThreadTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Measure RecyclerView performance.
 * This is inspired by https://github.com/android/performance-samples/tree/master/BenchmarkSample
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class RecyclerViewBenchmark {

  class LazyComputedList<T>(
    override val size: Int = Int.MAX_VALUE,
    private inline val compute: (Int) -> T
  ) : AbstractList<T>() {
    override fun get(index: Int): T = compute(index)
  }

  @get:Rule
  val benchmarkRule = BenchmarkRule()

  @get:Rule
  val activityRule = ActivityTestRule(MainActivity::class.java)

  @Before
  fun setup() {
    activityRule.runOnUiThread {
      val activity = activityRule.activity

      // set the RecyclerView to have a height of 1 pixel to ensure that only one item can be displayed at once.
      activity.recyclerView.layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, 1)

      activity.adapter.submitList(null)
      activity.adapter.submitList(LazyComputedList { buildRandomParagraph() })
    }
  }

  @Test
  @UiThreadTest
  fun scrollWithViewPool() {

    val recyclerView = activityRule.activity.recyclerView

    recyclerView.recycledViewPool.setMaxRecycledViews(0, 5)

    assertThat(recyclerView.childCount).isGreaterThan(0)
    assertThat(recyclerView.height).isEqualTo(1)

    // Due to the ViewPool, only onBindViewHolder is called and onCreateViewHolder will not be called.
    benchmarkRule.measureRepeated {
      recyclerView.scrollBy(0, recyclerView.getLastChild().height)
    }
  }

  @Test
  @UiThreadTest
  fun scrollWithoutViewPool() {

    val recyclerView = activityRule.activity.recyclerView

    recyclerView.recycledViewPool.setMaxRecycledViews(0, 0)

    assertThat(recyclerView.childCount).isGreaterThan(0)
    assertThat(recyclerView.height).isEqualTo(1)

    // No ViewPool is attached, so both onCreateViewHolder and onBindViewHolder are called
    benchmarkRule.measureRepeated {
      recyclerView.scrollBy(0, recyclerView.getLastChild().height)
    }
  }

  private fun ViewGroup.getLastChild(): View = getChildAt(childCount - 1)
}