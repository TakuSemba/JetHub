package com.takusemba.jethub.feed

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.model.Repo
import com.takusemba.jethub.model.Repo.Companion.createRepo
import com.takusemba.jethub.repository.SearchRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ObsoleteCoroutinesApi
@RunWith(JUnit4::class)
class FeedViewModelTest {

  @get:Rule var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  @MockK private lateinit var searchRepository: SearchRepository
  @MockK private lateinit var errorHandler: ErrorHandler

  private val dispatcher = TestCoroutineDispatcher()

  @Before
  @ExperimentalCoroutinesApi
  fun setUp() {
    MockKAnnotations.init(this)
    Dispatchers.setMain(dispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `initial state`() {
    dispatcher.runBlockingTest {

      val observer = mockk<Observer<List<Repo>>>(relaxed = true)

      coEvery { searchRepository.searchHotRepos(any()) } returns listOf(
        createRepo(id = 1, language = "Kotlin"),
        createRepo(id = 2, language = "Kotlin"),
        createRepo(id = 3, language = "Kotlin")
      )

      val viewModel = FeedViewModel(searchRepository, errorHandler)

      viewModel.hotRepos("Kotlin").observeForever(observer)

      verify { observer.onChanged(match { it.size == 3 }) }
    }
  }
}
