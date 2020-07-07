package com.takusemba.jethub.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.model.Repository.Companion.createRepository
import com.takusemba.jethub.repository.SearchRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
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
class SearchViewModelTest {

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

      val observer = mockk<Observer<List<Repository>>>(relaxed = true)

      coEvery { searchRepository.searchRepos("") } returns listOf(
        createRepository(id = 1),
        createRepository(id = 2),
        createRepository(id = 3)
      )

      val viewModel = SearchViewModel(searchRepository, errorHandler)

      viewModel.searchedRepos.observeForever(observer)

      verify { observer.onChanged(match { it.size == 3 }) }
    }
  }

  @Test
  fun `search repos`() {
    dispatcher.runBlockingTest {
      val observer = mockk<Observer<List<Repository>>>(relaxed = true)

      coEvery { searchRepository.searchRepos("") } returns emptyList()
      coEvery { searchRepository.searchRepos("something") } returns listOf(
        createRepository(id = 1),
        createRepository(id = 2),
        createRepository(id = 3)
      )

      val viewModel = SearchViewModel(searchRepository, errorHandler)

      viewModel.search("something")

      viewModel.searchedRepos.observeForever(observer)

      verify { observer.onChanged(match { it.size == 3 }) }
    }
  }
}
