package com.takusemba.jethub.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
class RepositoryEntity(
  @PrimaryKey
  var id: Int,
  var name: String,
  var owner: String
)