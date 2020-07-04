package com.takusemba.jethub.database.entity

import androidx.room.ColumnInfo
import com.takusemba.jethub.model.Owner
import org.jetbrains.annotations.TestOnly

class OwnerEntity(
  @ColumnInfo(name = "owner_id") val id: Int,
  val login: String,
  val avatarUrl: String,
  val url: String
) {

  fun toModel() = Owner(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    url = url
  )

  companion object {

    fun fromModel(owner: Owner): OwnerEntity {
      return OwnerEntity(
        id = owner.id,
        login = owner.login,
        avatarUrl = owner.avatarUrl,
        url = owner.url
      )
    }

    @TestOnly
    fun createOwnerEntity(
      id: Int = -1,
      login: String = "",
      avatarUrl: String = "",
      url: String = ""
    ) = OwnerEntity(
      id = id,
      login = login,
      avatarUrl = avatarUrl,
      url = url
    )
  }
}