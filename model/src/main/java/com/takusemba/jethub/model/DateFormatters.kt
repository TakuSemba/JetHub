package com.takusemba.jethub.model

import java.time.format.DateTimeFormatter

object DateFormatters {

  fun ofApiResult(): DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

  fun ofDatabase(): DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

  fun ofSearchQuery(): DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
}
