package com.mkhaled.movieapp.movieScreen.domain.usecase

import com.mkhaled.movieapp.core.data.cash.model.CashedItemData
import com.mkhaled.movieapp.core.domain.repositories.HomeRepositoryInterface
import com.mkhaled.movieapp.movieScreen.domain.model.MovieUIData
import com.mkhaled.movieapp.core.utils.DispatchersProvider
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

//class StoreMovie @Inject constructor(
//    private val homeRepositoryInterface: HomeRepositoryInterface,
//    private val dispatchersProvider: DispatchersProvider
//) {
//    suspend operator fun invoke(data: MovieUIData) {
//        withContext(dispatchersProvider.io()) {
//            val date = SimpleDateFormat("yyyy_MM_dd_HHmmss").format(Date())
//            homeRepositoryInterface.storeMovie(
//                CashedItemData(
//                    originalId = data.id,
//                    title = data.title,
//                    overview = data.overview,
//                    imgPath = data.image
//                )
//            )
//        }
//    }
//}