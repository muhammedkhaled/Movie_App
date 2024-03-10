package com.mkhaled.movieapp.core.data.cash

import com.mkhaled.movieapp.core.data.cash.model.CashedItemData
import io.reactivex.Flowable


interface Cache {
  suspend fun storeMovie(item: CashedItemData)
  fun getAll(lastUpdatedTime: Long): Flowable<List<CashedItemData>>
  suspend fun deleteAll()
}
