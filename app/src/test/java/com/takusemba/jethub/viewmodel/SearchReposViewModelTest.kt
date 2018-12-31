package com.takusemba.jethub.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.repository.SearchReposRepository
import com.takusemba.jethub.util.createRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ObsoleteCoroutinesApi
@RunWith(JUnit4::class)
class SearchReposViewModelTest {

  @get:Rule var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  @MockK private lateinit var searchReposRepository: SearchReposRepository

  private val mainThreadSurrogate = newSingleThreadContext("UI thread")

  @Before
  fun setUp() {
    MockKAnnotations.init(this)
    Dispatchers.setMain(mainThreadSurrogate)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
    mainThreadSurrogate.close()
  }

  @Test
  fun `initial state`() {
    runBlocking {

      val observer = mockk<Observer<List<Repository>>>(relaxed = true)

      coEvery { searchReposRepository.searchRepos("") } returns listOf(
        createRepository(id = 1),
        createRepository(id = 2),
        createRepository(id = 3)
      )

      val viewModel = SearchReposViewModel(searchReposRepository)

      viewModel.searchedRepos.observeForever(observer)
      viewModel.coroutineContext[Job]!!.children.forEach { it.join() }

      verify { observer.onChanged(match { it.size == 3 }) }
    }
  }

  @Test
  fun `search repos`() {
    runBlocking {
      val observer = mockk<Observer<List<Repository>>>(relaxed = true)

      coEvery { searchReposRepository.searchRepos("") } returns emptyList()
      coEvery { searchReposRepository.searchRepos("something") } returns listOf(
        createRepository(id = 1),
        createRepository(id = 2),
        createRepository(id = 3)
      )

      val viewModel = SearchReposViewModel(searchReposRepository)

      viewModel.search("something")

      viewModel.searchedRepos.observeForever(observer)
      viewModel.coroutineContext[Job]!!.children.forEach { it.join() }

      verify { observer.onChanged(match { it.size == 3 }) }
    }
  }
}
