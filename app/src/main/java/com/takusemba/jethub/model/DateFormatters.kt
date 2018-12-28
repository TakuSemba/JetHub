package com.takusemba.jethub.model

import org.threeten.bp.format.DateTimeFormatter

object DateFormatters {

  fun ofGithubApi() = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
}