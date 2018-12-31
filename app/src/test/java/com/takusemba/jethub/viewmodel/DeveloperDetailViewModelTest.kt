package com.takusemba.jethub.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.takusemba.jethub.repository.DeveloperDetailRepository
import com.takusemba.jethub.util.createDeveloper
import com.takusemba.jethub.util.createRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
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
class DeveloperDetailViewModelTest {

  @get:Rule var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  @MockK private lateinit var developerDetailRepository: DeveloperDetailRepository

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

      val name = "takusemba"
      coEvery { developerDetailRepository.getDeveloper(any()) } returns createDeveloper(name = name)
      coEvery { developerDetailRepository.getRepos(any()) } returns listOf(
        createRepository(id = 1),
        createRepository(id = 2),
        createRepository(id = 3)
      )

      val viewModel = DeveloperDetailViewModel(name, developerDetailRepository)

      viewModel.coroutineContext[Job]!!.children.forEach { it.join() }

      assertThat(viewModel.developer.value?.name).isEqualTo(name)
      assertThat(viewModel.developerRepos.value).hasSize(3)
    }
  }
}
