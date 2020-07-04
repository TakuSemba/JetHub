package com.takusemba.jethub.database

import com.google.common.truth.Truth.assertThat
import com.takusemba.jethub.database.dao.RepositoryDao
import com.takusemba.jethub.database.entity.RepositoryEntity.Companion.createRepositoryEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RepoDbClientTest {

  private lateinit var client: RepoDbClient

  @MockK private lateinit var mockRepositoryDao: RepositoryDao

  @Before
  fun setup() {
    MockKAnnotations.init(this)

    client = RepoDbClient(mockRepositoryDao)
  }

  @Test
  fun `get all`() {
    runBlocking {

      coEvery { mockRepositoryDao.getAll() } returns listOf(
        createRepositoryEntity(id = 1, name = "name-1"),
        createRepositoryEntity(id = 2, name = "name-2"),
        createRepositoryEntity(id = 3, name = "name-3")
      )

      val repos = client.getAll()

      assertThat(repos).hasSize(3)

      assertThat(repos[0].id).isEqualTo(1)
      assertThat(repos[0].name).isEqualTo("name-1")
      assertThat(repos[0].owner).isEqualTo("owner-1")

      assertThat(repos[1].id).isEqualTo(2)
      assertThat(repos[1].name).isEqualTo("name-2")
      assertThat(repos[1].owner).isEqualTo("owner-2")

      assertThat(repos[2].id).isEqualTo(3)
      assertThat(repos[2].name).isEqualTo("name-3")
      assertThat(repos[2].owner).isEqualTo("owner-3")
    }
  }
}