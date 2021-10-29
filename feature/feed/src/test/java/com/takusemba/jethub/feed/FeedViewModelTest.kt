package com.takusemba.jethub.feed

import com.google.common.truth.Truth.assertThat
import com.takusemba.jethub.base.ErrorHandler
import com.takusemba.jethub.model.Repo.Companion.createRepo
import com.takusemba.jethub.repository.SearchRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class FeedViewModelTest {

  @MockK private lateinit var searchRepository: SearchRepository
  @MockK private lateinit var errorHandler: ErrorHandler

  private val dispatcher = TestCoroutineDispatcher()

  @Before
  fun setUp() {
    MockKAnnotations.init(this)
    Dispatchers.setMain(dispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `initial state`() = runBlockingTest {
    val repositories = listOf(
      createRepo(id = 1, language = "Kotlin"),
      createRepo(id = 2, language = "Kotlin"),
      createRepo(id = 3, language = "Kotlin")
    )
    coEvery { searchRepository.searchHotRepos(any()) } returns repositories

    val viewModel = FeedViewModel(searchRepository, errorHandler)

    assertThat(viewModel.hotReposMap.value["Kotlin"]).isEqualTo(repositories)
  }
}
