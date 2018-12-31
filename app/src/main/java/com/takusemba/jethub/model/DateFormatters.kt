package com.takusemba.jethub.model

import org.threeten.bp.format.DateTimeFormatter

/**
 * list of formatters to be used in this app.
 */
object DateFormatters {

  fun ofApiResult(): DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

  fun ofSearchQuery(): DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
}