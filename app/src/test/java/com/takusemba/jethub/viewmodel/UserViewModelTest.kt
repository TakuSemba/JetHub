package com.takusemba.jethub.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.takusemba.jethub.repository.UserRepository
import com.takusemba.jethub.util.createRepository
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
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

  @MockK private lateinit var userRepository: UserRepository

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

      coEvery { userRepository.findAll() } returns listOf(
        createRepository(id = 1),
        createRepository(id = 2),
        createRepository(id = 3)
      )

      val userViewModel = UserViewModel(userRepository)

      userViewModel.coroutineContext[Job]!!.children.forEach { it.join() }

      assertThat(userViewModel.pinedRepositories.value).hasSize(3)
    }
  }

  @Test
  fun `pin repo`() {
    runBlocking {

      coEvery { userRepository.findAll() } returns listOf(
        createRepository(id = 1),
        createRepository(id = 2),
        createRepository(id = 3)
      )
      coEvery { userRepository.pin(any()) } just Runs

      val userViewModel = UserViewModel(userRepository)

      val repo = createRepository(id = 4)
      userViewModel.pin(repo)

      userViewModel.coroutineContext[Job]!!.children.forEach { it.join() }

      assertThat(userViewModel.pinedRepositories.value).hasSize(4)
    }
  }

  @Test
  fun `unpin repo`() {
    runBlocking {

      val repoToBeRemoved = createRepository(id = 3)
      coEvery { userRepository.findAll() } returns listOf(
        createRepository(id = 1),
        createRepository(id = 2),
        repoToBeRemoved
      )
      coEvery { userRepository.unpin(any()) } just Runs

      val userViewModel = UserViewModel(userRepository)

      userViewModel.unpin(repoToBeRemoved)

      userViewModel.coroutineContext[Job]!!.children.forEach { it.join() }

      assertThat(userViewModel.pinedRepositories.value).hasSize(2)
    }
  }
}