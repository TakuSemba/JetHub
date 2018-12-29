package com.takusemba.jethub.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.takusemba.jethub.database.dao.RepositoryDao
import com.takusemba.jethub.database.entity.RepositoryEntity

@Database(entities = [RepositoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

  abstract val repositoryDao: RepositoryDao
}