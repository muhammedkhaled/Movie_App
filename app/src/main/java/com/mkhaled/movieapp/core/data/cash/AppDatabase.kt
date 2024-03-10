package com.mkhaled.movieapp.core.data.cash

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mkhaled.movieapp.core.data.cash.daos.Dao
import com.mkhaled.movieapp.core.data.cash.model.CashedItemData


@Database(
    entities = [
      CashedItemData::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieDao(): Dao
}
