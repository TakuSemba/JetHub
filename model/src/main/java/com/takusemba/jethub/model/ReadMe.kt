package com.takusemba.jethub.model

import org.jetbrains.annotations.TestOnly

data class ReadMe(
  val name: String,
  val path: String,
  val content: String,
  val url: String,
  val gitUrl: String,
  val htmlUrl: String,
  val downloadUrl: String,
) {

  companion object {

    val EMPTY = ReadMe(
      name = "",
      path = "",
      content = "",
      url = "",
      gitUrl = "",
      htmlUrl = "",
      downloadUrl = "",
    )

    @TestOnly
    fun createReadMe(
      name: String = "name",
      path: String = "path",
      content: String = "content",
      url: String = "url",
      gitUrl: String = "gitUrl",
      htmlUrl: String = "htmlUrl",
      downloadUrl: String = "downloadUrl",
    ) = ReadMe(
      name = name,
      path = path,
      content = content,
      url = url,
      gitUrl = gitUrl,
      htmlUrl = htmlUrl,
      downloadUrl = downloadUrl,
    )
  }
}
