package com.takusemba.jethub.api.response

import com.takusemba.jethub.model.Owner
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Owner response data.
 */
@Serializable
class OwnerResponse {

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

  fun toModel() = Owner(
    id = id ?: throw IllegalArgumentException("id not found"),
    login = login ?: "",
    avatarUrl = avatarUrl ?: "",
    url = url ?: ""
  )
}
