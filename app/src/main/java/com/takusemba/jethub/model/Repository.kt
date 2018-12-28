package com.takusemba.jethub.model

import org.threeten.bp.LocalDateTime

class Repository(
  val id: Int,
  val owner: Owner,
  val name: String,
  val description: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
  val starsCount: Int,
  val watchersCount: Int,
  val forksCount: Int,
  val language: Language
)