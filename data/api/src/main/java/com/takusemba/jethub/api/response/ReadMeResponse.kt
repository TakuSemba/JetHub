package com.takusemba.jethub.api.response

import com.takusemba.jethub.model.ReadMe
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ReadMeResponse {

  @SerialName("type")
  var type: String? = null

  @SerialName("encoding")
  var encoding: String? = null

  @SerialName("size")
  var size: Int? = null

  @SerialName("name")
  var name: String? = null

  @SerialName("path")
  var path: String? = null

  @SerialName("content")
  var content: String? = null

  @SerialName("sha")
  var sha: String? = null

  @SerialName("url")
  var url: String? = null

  @SerialName("git_url")
  var gitUrl: String? = null

  @SerialName("html_url")
  var htmlUrl: String? = null

  @SerialName("download_url")
  var downloadUrl: String? = null

  fun toModel() = ReadMe(
    name = name ?: "",
    path = path ?: "",
    content = content ?: "",
    url = url ?: "",
    gitUrl = gitUrl ?: "",
    htmlUrl = htmlUrl ?: "",
    downloadUrl = downloadUrl ?: "",
  )
}
