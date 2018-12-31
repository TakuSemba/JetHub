package com.takusemba.jethub.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataTestUtil {

  /**
   * Gets the value of a LiveData safely.
   */
  @Throws(InterruptedException::class)
  fun <T> getValue(liveData: LiveData<T>): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
      override fun onChanged(o: T?) {
        data = o
        latch.countDown()
        liveData.removeObserver(this)
      }
    }
    liveData.observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)

    return data
  }
}