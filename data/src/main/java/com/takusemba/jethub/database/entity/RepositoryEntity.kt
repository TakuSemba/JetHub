package com.takusemba.jethub.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.takusemba.jethub.database.LocalDateTimeConverter
import com.takusemba.jethub.database.entity.OwnerEntity.Companion.createOwnerEntity
import com.takusemba.jethub.model.Repo
import org.jetbrains.annotations.TestOnly
import java.time.LocalDateTime

@Entity(tableName = "repository")
@TypeConverters(LocalDateTimeConverter::class)
class RepositoryEntity(
  @PrimaryKey val id: Int,
  @Embedded val owner: OwnerEntity,
  val name: String,
  val description: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
  val starsCount: Int,
  val watchersCount: Int,
  val forksCount: Int,
  val language: String
) {

  fun toModel() = Repo(
    id = id,
    owner = owner.toModel(),
    name = name,
    description = description,
    createdAt = createdAt,
    updatedAt = updatedAt,
    starsCount = starsCount,
    watchersCount = watchersCount,
    forksCount = forksCount,
    language = language
  )

  companion object {

    fun fromModel(repo: Repo): RepositoryEntity {
      return RepositoryEntity(
        id = repo.id,
        owner = OwnerEntity.fromModel(repo.owner),
        name = repo.name,
        description = repo.description,
        createdAt = repo.createdAt,
        updatedAt = repo.updatedAt,
        starsCount = repo.starsCount,
        watchersCount = repo.watchersCount,
        forksCount = repo.forksCount,
        language = repo.language
      )
    }

    @TestOnly
    fun createRepositoryEntity(
      id: Int = -1,
      owner: OwnerEntity = createOwnerEntity(),
      name: String = "",
      description: String = "",
      createdAt: LocalDateTime = LocalDateTime.now(),
      updatedAt: LocalDateTime = LocalDateTime.now(),
      starsCount: Int = 0,
      watchersCount: Int = 0,
      forksCount: Int = 0,
      language: String = "Kotlin"
    ) = RepositoryEntity(
      id = id,
      owner = owner,
      name = name,
      description = description,
      createdAt = createdAt,
      updatedAt = updatedAt,
      starsCount = starsCount,
      watchersCount = watchersCount,
      forksCount = forksCount,
      language = language
    )
  }
}
