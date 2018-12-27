package com.takusemba.jethub.api.response

import com.google.gson.annotations.SerializedName
import com.takusemba.jethub.model.User

class UserResponse : DataResponse<User>() {

  @SerializedName("login")
  val login: String? = null

  @SerializedName("id")
  val id: Long? = null

  @SerializedName("node_id")
  val nodeId: String? = null

  @SerializedName("avatar_url")
  val avatarUrl: String? = null

  @SerializedName("gravatar_id")
  val gravatarId: String? = null

  @SerializedName("url")
  val url: String? = null

  @SerializedName("html_url")
  val htmlUrl: String? = null

  @SerializedName("followers_url")
  val followersUrl: String? = null

  @SerializedName("following_url")
  val followingUrl: String? = null

  @SerializedName("gists_url")
  val gistsUrl: String? = null

  @SerializedName("starred_url")
  val starredUrl: String? = null

  @SerializedName("subscriptions_url")
  val subscriptionsUrl: String? = null

  @SerializedName("organizations_url")
  val organizationsUrl: String? = null

  @SerializedName("repos_url")
  val reposUrl: String? = null

  @SerializedName("events_url")
  val eventsUrl: String? = null

  @SerializedName("received_events_url")
  val receivedEventsUrl: String? = null

  @SerializedName("type")
  val type: String? = null

  @SerializedName("site_admin")
  val siteAdmin: Boolean? = null

  @SerializedName("name")
  val name: String? = null

  @SerializedName("company")
  val company: String? = null

  @SerializedName("blog")
  val blog: String? = null

  @SerializedName("location")
  val location: String? = null

  @SerializedName("email")
  val email: String? = null

  @SerializedName("hireable")
  val hireable: String? = null

  @SerializedName("bio")
  val bio: String? = null

  @SerializedName("public_repos")
  val publicRepos: Int? = null

  @SerializedName("public_gists")
  val publicGists: Int? = null

  @SerializedName("followers")
  val followers: Int? = null

  @SerializedName("following")
  val following: Int? = null

  @SerializedName("created_at")
  val createdAt: String? = null

  @SerializedName("updated_at")
  val updatedAt: String? = null

  override fun toModel() = User(
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