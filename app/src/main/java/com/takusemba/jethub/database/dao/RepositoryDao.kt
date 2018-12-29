package com.takusemba.jethub.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.takusemba.jethub.database.entity.RepositoryEntity

@Dao
abstract class RepositoryDao {

  @Query("SELECT * FROM repository")
  abstract fun getAll(): List<RepositoryEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun insert(repositoryEntity: RepositoryEntity)

  @Delete
  abstract fun delete(repositoryEntity: RepositoryEntity)

  @Query("DELETE FROM repository")
  abstract fun deleteAll()
}

