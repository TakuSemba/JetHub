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

@RunWith(JUnit4::class)
class DeveloperApiClientTest {

  @get:Rule
  val mockWebServer = MockWebServer()

  private lateinit var client: DeveloperApiClient

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
    client = DeveloperApiClient(retrofit)
  }

  @Test
  fun `get developer`() {
    runBlocking {
      val json = """
      {
          "login": "TakuSemba",
          "id": 13956869,
          "node_id": "MDQ6VXNlcjEzOTU2ODY5",
          "avatar_url": "https://avatars3.githubusercontent.com/u/13956869",
          "gravatar_id": "",
          "url": "https://api.github.com/users/TakuSemba",
          "html_url": "https://github.com/TakuSemba",
          "followers_url": "https://api.github.com/users/TakuSemba/followers",
          "following_url": "https://api.github.com/users/TakuSemba/following{/other_user}",
          "gists_url": "https://api.github.com/users/TakuSemba/gists{/gist_id}",
          "starred_url": "https://api.github.com/users/TakuSemba/starred{/owner}{/repo}",
          "subscriptions_url": "https://api.github.com/users/TakuSemba/subscriptions",
          "organizations_url": "https://api.github.com/users/TakuSemba/orgs",
          "repos_url": "https://api.github.com/users/TakuSemba/repos",
          "events_url": "https://api.github.com/users/TakuSemba/events{/privacy}",
          "received_events_url": "https://api.github.com/users/TakuSemba/received_events",
          "type": "User",
          "site_admin": false,
          "name": "TakuSemba",
          "company": null,
          "blog": "",
          "location": "Tokyo, Japan",
          "email": null,
          "hireable": null,
          "bio": "CyberAgent.Inc, AbemaTv.Inc\r\n@abema",
          "public_repos": 42,
          "public_gists": 34,
          "followers": 219,
          "following": 28,
          "created_at": "2015-08-25T04:37:48Z",
          "updated_at": "2019-01-03T07:05:41Z"
      }
    """.trimIndent()

      mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json))

      val developer = client.getDeveloper("TakuSemba")

      assertThat(developer.id).isEqualTo(13956869)
      assertThat(developer.login).isEqualTo("TakuSemba")
      assertThat(developer.avatarUrl).isEqualTo("https://avatars3.githubusercontent.com/u/13956869")
      assertThat(developer.name).isEqualTo("TakuSemba")
      assertThat(developer.company).isEqualTo("")
      assertThat(developer.blog).isEqualTo("")
      assertThat(developer.location).isEqualTo("Tokyo, Japan")
      assertThat(developer.email).isEqualTo("")
      assertThat(developer.bio).isEqualTo("CyberAgent.Inc, AbemaTv.Inc\r\n@abema")
      assertThat(developer.publicRepositoriesCount).isEqualTo(42)
      assertThat(developer.publicGistsCount).isEqualTo(34)
      assertThat(developer.followersCount).isEqualTo(219)
      assertThat(developer.followingCount).isEqualTo(28)
    }
  }

  @Test
  fun `get getRepos`() {
    runBlocking {
      val json = """
        [
            {
                "id": 108085495,
                "node_id": "MDEwOlJlcG9zaXRvcnkxMDgwODU0OTU=",
                "name": "AdapterDelegates",
                "full_name": "TakuSemba/AdapterDelegates",
                "private": false,
                "owner": {
                    "login": "TakuSemba",
                    "id": 13956869,
                    "node_id": "MDQ6VXNlcjEzOTU2ODY5",
                    "avatar_url": "https://avatars3.githubusercontent.com/u/13956869?v=4",
                    "gravatar_id": "",
                    "type": "User",
                    "site_admin": false
                },
                "html_url": "https://github.com/TakuSemba/AdapterDelegates",
                "description": "\"Favor composition over inheritance\" for RecyclerView Adapters",
                "fork": true,
                "created_at": "2017-10-24T06:22:07Z",
                "updated_at": "2017-10-24T06:22:09Z",
                "pushed_at": "2017-09-04T10:48:29Z",
                "size": 760,
                "stargazers_count": 0,
                "watchers_count": 0,
                "language": "Java",
                "has_issues": false,
                "has_projects": true,
                "has_downloads": true,
                "has_wiki": true,
                "has_pages": false,
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
                "watchers": 0,
                "default_branch": "master"
            },
            {
                "id": 119484317,
                "node_id": "MDEwOlJlcG9zaXRvcnkxMTk0ODQzMTc=",
                "name": "android-jenkins-docker-using-sdkmanager",
                "full_name": "TakuSemba/android-jenkins-docker-using-sdkmanager",
                "private": false,
                "owner": {
                    "login": "TakuSemba",
                    "id": 13956869,
                    "node_id": "MDQ6VXNlcjEzOTU2ODY5",
                    "avatar_url": "https://avatars3.githubusercontent.com/u/13956869?v=4",
                    "type": "User",
                    "site_admin": false
                },
                "html_url": "https://github.com/TakuSemba/android-jenkins-docker-using-sdkmanager",
                "description": "Docker image with Jenkins that can build Android apps.",
                "fork": true,
                "homepage": null,
                "created_at": "2018-01-30T04:52:59Z",
                "updated_at": "2018-07-07T17:28:56Z",
                "pushed_at": "2018-01-30T08:19:39Z",
                "size": 19,
                "stargazers_count": 2,
                "watchers_count": 2,
                "language": null,
                "has_issues": false,
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
                "watchers": 2,
                "default_branch": "master"
            }
        ]
    """.trimIndent()

      mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json))

      val repos = client.getRepos("TakuSemba")

      assertThat(repos).hasSize(2)

      assertThat(repos[0].id).isEqualTo(108085495)
      assertThat(repos[0].name).isEqualTo("AdapterDelegates")
      assertThat(repos[0].createdAt.toString()).isEqualTo("2017-10-24T06:22:07")
      assertThat(repos[0].updatedAt.toString()).isEqualTo("2017-10-24T06:22:09")

      assertThat(repos[1].id).isEqualTo(119484317)
      assertThat(repos[1].name).isEqualTo("android-jenkins-docker-using-sdkmanager")
      assertThat(repos[1].createdAt.toString()).isEqualTo("2018-01-30T04:52:59")
      assertThat(repos[1].updatedAt.toString()).isEqualTo("2018-07-07T17:28:56")
    }
  }
}