package com.takusemba.jethub.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.takusemba.jethub.database.entity.OwnerEntity.Companion.createOwnerEntity
import com.takusemba.jethub.model.DateFormatters
import com.takusemba.jethub.model.Repository
import org.jetbrains.annotations.TestOnly
import java.time.LocalDateTime

@Entity(tableName = "repository")
class RepositoryEntity(
  @PrimaryKey
  val id: Int,
  val owner: OwnerEntity,
  val name: String,
  val description: String,
  val createdAt: String,
  val updatedAt: String,
  val starsCount: Int,
  val watchersCount: Int,
  val forksCount: Int,
  val language: String
) {

  fun toModel() = Repository(
    id = id,
    owner = owner.toModel(),
    name = name,
    description = description,
    createdAt = LocalDateTime.from(DateFormatters.ofDatabase().parse(createdAt)),
    updatedAt = LocalDateTime.from(DateFormatters.ofDatabase().parse(updatedAt)),
    starsCount = starsCount,
    watchersCount = watchersCount,
    forksCount = forksCount,
    language = language
  )

  companion object {

    fun fromModel(repository: Repository): RepositoryEntity {
      return RepositoryEntity(
        id = repository.id,
        owner = OwnerEntity.fromModel(repository.owner),
        name = repository.name,
        description = repository.description,
        createdAt = repository.createdAt.format(DateFormatters.ofDatabase()),
        updatedAt = repository.updatedAt.format(DateFormatters.ofDatabase()),
        starsCount = repository.starsCount,
        watchersCount = repository.watchersCount,
        forksCount = repository.forksCount,
        language = repository.language
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
      createdAt = createdAt.format(DateFormatters.ofDatabase()),
      updatedAt = updatedAt.format(DateFormatters.ofDatabase()),
      starsCount = starsCount,
      watchersCount = watchersCount,
      forksCount = forksCount,
      language = language
    )
  }
}