package com.takusemba.jethub.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.model.Repo.Companion.createRepo
import com.takusemba.jethub.repository.RepoRepository
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

  @get:Rule var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  @MockK private lateinit var repoRepository: RepoRepository
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
      createRepo(id = 1),
      createRepo(id = 2),
      createRepo(id = 3)
    )
    coEvery { repoRepository.findAllPins() } returns repositories

    val viewModel = UserViewModel(repoRepository, errorHandler)

    assertThat(viewModel.pinedRepositories.value).isEqualTo(repositories)
  }

  @Test
  fun `pin repo`() = runBlockingTest {

    val repositories = listOf(
      createRepo(id = 1),
      createRepo(id = 2),
      createRepo(id = 3)
    )
    coEvery { repoRepository.findAllPins() } returns repositories
    coEvery { repoRepository.pin(any()) } just Runs

    val viewModel = UserViewModel(repoRepository, errorHandler)

    val repoToBeAdded = createRepo(id = 4)
    viewModel.pin(repoToBeAdded)

    assertThat(viewModel.pinedRepositories.value).isEqualTo(repositories + repoToBeAdded)
  }

  @Test
  fun `unpin repo`() = runBlockingTest {

    val repoToBeRemoved = createRepo(id = 3)
    val repositories = listOf(
      createRepo(id = 1),
      createRepo(id = 2),
      repoToBeRemoved
    )
    coEvery { repoRepository.findAllPins() } returns repositories
    coEvery { repoRepository.unpin(any()) } just Runs

    val viewModel = UserViewModel(repoRepository, errorHandler)

    viewModel.unpin(repoToBeRemoved)

    assertThat(viewModel.pinedRepositories.value).isEqualTo(repositories - repoToBeRemoved)
  }
}
