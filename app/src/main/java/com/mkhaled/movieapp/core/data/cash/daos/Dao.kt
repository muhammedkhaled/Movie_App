package com.mkhaled.movieapp.core.data.cash.daos

import androidx.room.*
import androidx.room.Dao
import com.mkhaled.movieapp.core.data.cash.model.CashedItemData
import io.reactivex.Flowable

@Dao
abstract class Dao {

    @Transaction
    @Query("SELECT * FROM movie WHERE update_time >= :lastUpdatedTime")
    abstract fun getAll(lastUpdatedTime: Long): Flowable<List<CashedItemData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertItem(item: CashedItemData)

    @Query("DELETE FROM movie")
    abstract suspend fun deleteAll()
}
