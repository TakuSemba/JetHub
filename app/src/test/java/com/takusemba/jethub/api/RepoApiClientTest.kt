package com.takusemba.jethub.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class RepoApiClientTest {

  @get:Rule
  val mockWebServer = MockWebServer()

  private lateinit var client: RepoApiClient

  @Before
  fun setUp() {
    val retrofit = Retrofit.Builder()
      .baseUrl(mockWebServer.url("/").toString())
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .build()
    client = RepoApiClient(retrofit)
  }

  @Test
  fun `get repo`() {
    runBlocking {
      val json = """
          {
              "id": 163260752,
              "node_id": "MDEwOlJlcG9zaXRvcnkxNjMyNjA3NTI=",
              "name": "JetHub",
              "full_name": "TakuSemba/JetHub",
              "private": false,
              "owner": {
                  "login": "TakuSemba",
                  "id": 13956869,
                  "node_id": "MDQ6VXNlcjEzOTU2ODY5",
                  "avatar_url": "https://avatars3.githubusercontent.com/u/13956869?v=4",
                  "gravatar_id": "",
                  "url": "https://api.github.com/users/TakuSemba",
                  "type": "User",
                  "site_admin": false
              },
              "html_url": "https://github.com/TakuSemba/JetHub",
              "description": "Sample App with Jetpack components(LiveData, Navigation, ViewModel) + MVVM + coroutine + single activity",
              "fork": false,
              "url": "https://api.github.com/repos/TakuSemba/JetHub",
              "created_at": "2018-12-27T07:12:15Z",
              "updated_at": "2019-01-03T07:43:11Z",
              "pushed_at": "2019-01-01T05:04:20Z",
              "git_url": "git://github.com/TakuSemba/JetHub.git",
              "ssh_url": "git@github.com:TakuSemba/JetHub.git",
              "clone_url": "https://github.com/TakuSemba/JetHub.git",
              "svn_url": "https://github.com/TakuSemba/JetHub",
              "homepage": null,
              "size": 1801,
              "stargazers_count": 5,
              "watchers_count": 5,
              "language": "Kotlin",
              "has_issues": true,
              "has_projects": true,
              "has_downloads": true,
              "has_wiki": true,
              "has_pages": false,
              "forks_count": 1,
              "mirror_url": null,
              "archived": false,
              "open_issues_count": 0,
              "license": {
                  "key": "apache-2.0",
                  "name": "Apache License 2.0",
                  "spdx_id": "Apache-2.0",
                  "url": "https://api.github.com/licenses/apache-2.0",
                  "node_id": "MDc6TGljZW5zZTI="
              },
              "forks": 1,
              "open_issues": 0,
              "watchers": 5,
              "default_branch": "master",
              "network_count": 1,
              "subscribers_count": 1
          }
    """.trimIndent()

      mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json))

      val repo = client.getRepo("TakuSemba", "JetHub")

      assertThat(repo.id).isEqualTo(163260752)
      assertThat(repo.name).isEqualTo("JetHub")
      assertThat(repo.createdAt.toString()).isEqualTo("2018-12-27T07:12:15")
      assertThat(repo.updatedAt.toString()).isEqualTo("2019-01-03T07:43:11")
    }
  }
}