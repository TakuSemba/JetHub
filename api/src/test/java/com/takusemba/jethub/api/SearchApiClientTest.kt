package com.takusemba.jethub.api

import com.google.common.truth.Truth.assertThat
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class SearchApiClientTest {

  @get:Rule
  val mockWebServer = MockWebServer()

  private lateinit var client: SearchApiClient

  @Before
  fun setUp() {
    val converterFactory = Json(JsonConfiguration.Stable.copy(
      isLenient = true,
      ignoreUnknownKeys = true
    )).asConverterFactory("application/json".toMediaType())
    val retrofit = Retrofit.Builder()
      .baseUrl(mockWebServer.url("/").toString())
      .addConverterFactory(converterFactory)
      .build()
    client = SearchApiClient(retrofit)
  }

  @Test
  fun `get hot developers`() {
    runBlocking {
      val json = """
          {
              "total_count": 116,
              "incomplete_results": false,
              "items": [
                    {
                    "login": "nekokotlin",
                    "id": 46165431,
                    "node_id": "MDQ6VXNlcjQ2MTY1NDMx",
                    "avatar_url": "https://avatars1.githubusercontent.com/u/46165431?v=4",
                    "gravatar_id": "",
                    "type": "User",
                    "site_admin": false,
                    "score": 1
                },
                {
                    "login": "LowPowerTime",
                    "id": 46289070,
                    "node_id": "MDQ6VXNlcjQ2Mjg5MDcw",
                    "avatar_url": "https://avatars2.githubusercontent.com/u/46289070?v=4",
                    "gravatar_id": "",
                    "type": "User",
                    "site_admin": false,
                    "score": 1
                }
              ]
          }
    """.trimIndent()

      mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json))

      val developers = client.getHotDevelopers("Kotlin", LocalDateTime.now())

      assertThat(developers).hasSize(2)

      assertThat(developers[0].id).isEqualTo(46165431)
      assertThat(developers[0].login).isEqualTo("nekokotlin")

      assertThat(developers[1].id).isEqualTo(46289070)
      assertThat(developers[1].login).isEqualTo("LowPowerTime")
    }
  }

  @Test
  fun `get hot repos`() {
    runBlocking {
      val json = """
          {
              "total_count": 116,
              "incomplete_results": false,
              "items": [
                  {
                      "id": 164134392,
                      "node_id": "MDEwOlJlcG9zaXRvcnkxNjQxMzQzOTI=",
                      "name": "GradleKotlinConverter",
                      "full_name": "bernaferrari/GradleKotlinConverter",
                      "private": false,
                      "owner": {
                          "login": "bernaferrari",
                          "id": 351125,
                          "node_id": "MDQ6VXNlcjM1MTEyNQ==",
                          "avatar_url": "https://avatars2.githubusercontent.com/u/351125?v=4",
                          "gravatar_id": "",
                          "type": "User",
                          "site_admin": false
                      },
                      "html_url": "https://github.com/bernaferrari/GradleKotlinConverter",
                      "description": "Convert from Groovy to Kotlin DSL for Gradle, focused on Android.",
                      "fork": false,
                      "url": "https://api.github.com/repos/bernaferrari/GradleKotlinConverter",
                      "created_at": "2019-01-04T17:10:23Z",
                      "updated_at": "2019-01-07T12:50:32Z",
                      "pushed_at": "2019-01-04T21:30:03Z",
                      "homepage": "",
                      "size": 19,
                      "stargazers_count": 104,
                      "watchers_count": 104,
                      "language": "Kotlin",
                      "forks_count": 0,
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
                      "forks": 0,
                      "open_issues": 0,
                      "watchers": 104,
                      "default_branch": "master",
                      "score": 1
                  },
                  {
                      "id": 163664038,
                      "node_id": "MDEwOlJlcG9zaXRvcnkxNjM2NjQwMzg=",
                      "name": "kompile-testing",
                      "full_name": "permissions-dispatcher/kompile-testing",
                      "private": false,
                      "owner": {
                          "login": "permissions-dispatcher",
                          "id": 31121294,
                          "node_id": "MDEyOk9yZ2FuaXphdGlvbjMxMTIxMjk0",
                          "avatar_url": "https://avatars3.githubusercontent.com/u/31121294?v=4",
                          "gravatar_id": "",
                          "type": "Organization",
                          "site_admin": false
                      },
                      "html_url": "https://github.com/permissions-dispatcher/kompile-testing",
                      "description": "Testing tools for kotlinc and kapt",
                      "fork": false,
                      "url": "https://api.github.com/repos/permissions-dispatcher/kompile-testing",
                      "created_at": "2018-12-31T11:36:25Z",
                      "updated_at": "2019-01-07T12:59:48Z",
                      "pushed_at": "2019-01-05T08:23:47Z",
                      "homepage": "",
                      "size": 74,
                      "stargazers_count": 34,
                      "watchers_count": 34,
                      "language": "Kotlin",
                      "forks_count": 1,
                      "mirror_url": null,
                      "archived": false,
                      "open_issues_count": 3,
                      "license": {
                          "key": "apache-2.0",
                          "name": "Apache License 2.0",
                          "spdx_id": "Apache-2.0",
                          "url": "https://api.github.com/licenses/apache-2.0",
                          "node_id": "MDc6TGljZW5zZTI="
                      },
                      "forks": 1,
                      "open_issues": 3,
                      "watchers": 34,
                      "default_branch": "master",
                      "score": 1
                  }
              ]
          }
    """.trimIndent()

      mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json))

      val repos = client.getHotRepos("Kotlin", LocalDateTime.now())

      assertThat(repos).hasSize(2)

      assertThat(repos[0].id).isEqualTo(164134392)
      assertThat(repos[0].name).isEqualTo("GradleKotlinConverter")

      assertThat(repos[1].id).isEqualTo(163664038)
      assertThat(repos[1].name).isEqualTo("kompile-testing")
    }
  }

  @Test
  fun `search developers`() {
    runBlocking {
      val json = """
          {
              "total_count": 116,
              "incomplete_results": false,
              "items": [
                {
                    "login": "nekokotlin",
                    "id": 46165431,
                    "node_id": "MDQ6VXNlcjQ2MTY1NDMx",
                    "avatar_url": "https://avatars1.githubusercontent.com/u/46165431?v=4",
                    "gravatar_id": "",
                    "type": "User",
                    "site_admin": false,
                    "score": 1
                },
                {
                    "login": "LowPowerTime",
                    "id": 46289070,
                    "node_id": "MDQ6VXNlcjQ2Mjg5MDcw",
                    "avatar_url": "https://avatars2.githubusercontent.com/u/46289070?v=4",
                    "gravatar_id": "",
                    "type": "User",
                    "site_admin": false,
                    "score": 1
                }
              ]
          }
    """.trimIndent()

      mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json))

      val developers = client.searchDevelopers("query")

      assertThat(developers).hasSize(2)

      assertThat(developers[0].id).isEqualTo(46165431)
      assertThat(developers[0].login).isEqualTo("nekokotlin")

      assertThat(developers[1].id).isEqualTo(46289070)
      assertThat(developers[1].login).isEqualTo("LowPowerTime")
    }
  }

  @Test
  fun `search repos`() {
    runBlocking {
      val json = """
          {
              "total_count": 116,
              "incomplete_results": false,
              "items": [
                  {
                      "id": 164134392,
                      "node_id": "MDEwOlJlcG9zaXRvcnkxNjQxMzQzOTI=",
                      "name": "GradleKotlinConverter",
                      "full_name": "bernaferrari/GradleKotlinConverter",
                      "private": false,
                      "owner": {
                          "login": "bernaferrari",
                          "id": 351125,
                          "node_id": "MDQ6VXNlcjM1MTEyNQ==",
                          "avatar_url": "https://avatars2.githubusercontent.com/u/351125?v=4",
                          "gravatar_id": "",
                          "type": "User",
                          "site_admin": false
                      },
                      "html_url": "https://github.com/bernaferrari/GradleKotlinConverter",
                      "description": "Convert from Groovy to Kotlin DSL for Gradle, focused on Android.",
                      "fork": false,
                      "url": "https://api.github.com/repos/bernaferrari/GradleKotlinConverter",
                      "created_at": "2019-01-04T17:10:23Z",
                      "updated_at": "2019-01-07T12:50:32Z",
                      "pushed_at": "2019-01-04T21:30:03Z",
                      "homepage": "",
                      "size": 19,
                      "stargazers_count": 104,
                      "watchers_count": 104,
                      "language": "Kotlin",
                      "forks_count": 0,
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
                      "forks": 0,
                      "open_issues": 0,
                      "watchers": 104,
                      "default_branch": "master",
                      "score": 1
                  },
                  {
                      "id": 163664038,
                      "node_id": "MDEwOlJlcG9zaXRvcnkxNjM2NjQwMzg=",
                      "name": "kompile-testing",
                      "full_name": "permissions-dispatcher/kompile-testing",
                      "private": false,
                      "owner": {
                          "login": "permissions-dispatcher",
                          "id": 31121294,
                          "node_id": "MDEyOk9yZ2FuaXphdGlvbjMxMTIxMjk0",
                          "avatar_url": "https://avatars3.githubusercontent.com/u/31121294?v=4",
                          "gravatar_id": "",
                          "type": "Organization",
                          "site_admin": false
                      },
                      "html_url": "https://github.com/permissions-dispatcher/kompile-testing",
                      "description": "Testing tools for kotlinc and kapt",
                      "fork": false,
                      "url": "https://api.github.com/repos/permissions-dispatcher/kompile-testing",
                      "created_at": "2018-12-31T11:36:25Z",
                      "updated_at": "2019-01-07T12:59:48Z",
                      "pushed_at": "2019-01-05T08:23:47Z",
                      "homepage": "",
                      "size": 74,
                      "stargazers_count": 34,
                      "watchers_count": 34,
                      "language": "Kotlin",
                      "forks_count": 1,
                      "mirror_url": null,
                      "archived": false,
                      "open_issues_count": 3,
                      "license": {
                          "key": "apache-2.0",
                          "name": "Apache License 2.0",
                          "spdx_id": "Apache-2.0",
                          "url": "https://api.github.com/licenses/apache-2.0",
                          "node_id": "MDc6TGljZW5zZTI="
                      },
                      "forks": 1,
                      "open_issues": 3,
                      "watchers": 34,
                      "default_branch": "master",
                      "score": 1
                  }
              ]
          }
    """.trimIndent()

      mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json))

      val repos = client.searchRepos("query")

      assertThat(repos).hasSize(2)

      assertThat(repos[0].id).isEqualTo(164134392)
      assertThat(repos[0].name).isEqualTo("GradleKotlinConverter")

      assertThat(repos[1].id).isEqualTo(163664038)
      assertThat(repos[1].name).isEqualTo("kompile-testing")
    }
  }
}