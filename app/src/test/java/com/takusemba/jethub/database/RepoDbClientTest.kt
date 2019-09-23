package com.takusemba.jethub.database

import com.google.common.truth.Truth.assertThat
import com.takusemba.jethub.database.dao.RepositoryDao
import com.takusemba.jethub.database.entity.RepositoryEntity
import io.mockk.MockKAnnotations
import io.mockk.every
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

      every { mockRepositoryDao.getAll() } returns listOf(
        RepositoryEntity(1, "name-1", "owner-1"),
        RepositoryEntity(2, "name-2", "owner-2"),
        RepositoryEntity(3, "name-3", "owner-3")
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