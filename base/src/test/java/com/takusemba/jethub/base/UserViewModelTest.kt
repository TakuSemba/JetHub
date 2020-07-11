package com.takusemba.jethub.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.takusemba.jethub.base.viewmodel.UserViewModel
import com.takusemba.jethub.model.Repo
import com.takusemba.jethub.model.Repo.Companion.createRepo
import com.takusemba.jethub.repository.RepoRepository
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.just
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
class UserViewModelTest {

  @get:Rule var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  @MockK private lateinit var repoRepository: RepoRepository
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

      coEvery { repoRepository.findAllPins() } returns listOf(
        createRepo(id = 1),
        createRepo(id = 2),
        createRepo(id = 3)
      )

      val viewModel = UserViewModel(repoRepository, errorHandler)

      viewModel.pinedRepositories.observeForever(observer)

      verify { observer.onChanged(match { it.size == 3 }) }
    }
  }

  @Test
  fun `pin repo`() {
    dispatcher.runBlockingTest {

      val observer = mockk<Observer<List<Repo>>>(relaxed = true)

      coEvery { repoRepository.findAllPins() } returns listOf(
        createRepo(id = 1),
        createRepo(id = 2),
        createRepo(id = 3)
      )
      coEvery { repoRepository.pin(any()) } just Runs

      val viewModel = UserViewModel(repoRepository, errorHandler)

      val repo = createRepo(id = 4)
      viewModel.pin(repo)

      viewModel.pinedRepositories.observeForever(observer)

      verify { observer.onChanged(match { it.size == 4 }) }
    }
  }

  @Test
  fun `unpin repo`() {
    dispatcher.runBlockingTest {

      val observer = mockk<Observer<List<Repo>>>(relaxed = true)
      val repoToBeRemoved = createRepo(id = 3)

      coEvery { repoRepository.findAllPins() } returns listOf(
        createRepo(id = 1),
        createRepo(id = 2),
        repoToBeRemoved
      )
      coEvery { repoRepository.unpin(any()) } just Runs

      val viewModel = UserViewModel(repoRepository, errorHandler)

      viewModel.unpin(repoToBeRemoved)

      viewModel.pinedRepositories.observeForever(observer)

      verify { observer.onChanged(match { it.size == 2 }) }
    }
  }
}
