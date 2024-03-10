package com.mkhaled.movieapp.core.data.cash

import com.mkhaled.movieapp.core.data.cash.daos.Dao
import javax.inject.Inject

import com.mkhaled.movieapp.core.data.cash.model.CashedItemData
import io.reactivex.Flowable

class RoomCache @Inject constructor(
    private val dao: Dao,
) : Cache {
    override suspend fun storeMovie(item: CashedItemData) = dao.insertItem(item)
    override fun getAll(lastUpdatedTime: Long): Flowable<List<CashedItemData>> = dao.getAll(lastUpdatedTime)
    override suspend fun deleteAll() = dao.deleteAll()

}

