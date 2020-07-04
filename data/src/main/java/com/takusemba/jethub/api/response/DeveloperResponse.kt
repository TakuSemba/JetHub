package com.takusemba.jethub.api.response

import com.takusemba.jethub.model.Developer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Developer response data.
 */
@Serializable
class DeveloperResponse {

  @SerialName("login")
  var login: String? = null

  @SerialName("id")
  var id: Int? = null

  @SerialName("node_id")
  var nodeId: String? = null

  @SerialName("avatar_url")
  var avatarUrl: String? = null

  @SerialName("gravatar_id")
  var gravatarId: String? = null

  @SerialName("url")
  var url: String? = null

  @SerialName("html_url")
  var htmlUrl: String? = null

  @SerialName("followers_url")
  var followersUrl: String? = null

  @SerialName("following_url")
  var followingUrl: String? = null

  @SerialName("gists_url")
  var gistsUrl: String? = null

  @SerialName("starred_url")
  var starredUrl: String? = null

  @SerialName("subscriptions_url")
  var subscriptionsUrl: String? = null

  @SerialName("organizations_url")
  var organizationsUrl: String? = null

  @SerialName("repos_url")
  var reposUrl: String? = null

  @SerialName("events_url")
  var eventsUrl: String? = null

  @SerialName("received_events_url")
  var receivedEventsUrl: String? = null

  @SerialName("type")
  var type: String? = null

  @SerialName("site_admin")
  var siteAdmin: Boolean? = null

  @SerialName("name")
  var name: String? = null

  @SerialName("company")
  var company: String? = null

  @SerialName("blog")
  var blog: String? = null

  @SerialName("location")
  var location: String? = null

  @SerialName("email")
  var email: String? = null

  @SerialName("hireable")
  var hireable: String? = null

  @SerialName("bio")
  var bio: String? = null

  @SerialName("public_repos")
  var publicRepos: Int? = null

  @SerialName("public_gists")
  var publicGists: Int? = null

  @SerialName("followers")
  var followers: Int? = null

  @SerialName("following")
  var following: Int? = null

  @SerialName("created_at")
  var createdAt: String? = null

  @SerialName("updated_at")
  var updatedAt: String? = null

  fun toModel() = Developer(
    id = id ?: throw IllegalArgumentException("id not found"),
    login = login ?: "",
    avatarUrl = avatarUrl ?: "",
    name = name ?: "",
    company = company ?: "",
    blog = blog ?: "",
    location = location ?: "",
    email = email ?: "",
    bio = bio ?: "",
    publicRepositoriesCount = publicRepos ?: 0,
    publicGistsCount = publicGists ?: 0,
    followersCount = followers ?: 0,
    followingCount = following ?: 0
  )
}