package com.mkhaled.movieapp.core.data

import com.mkhaled.movieapp.core.data.api.GetMovieApi
import com.mkhaled.movieapp.core.data.api.model.mappers.MovieMapper
import com.mkhaled.movieapp.core.data.cash.Cache
import com.mkhaled.movieapp.core.data.cash.model.CashedItemData
import com.mkhaled.movieapp.core.domain.model.NetworkException
import com.mkhaled.movieapp.core.domain.repositories.HomeRepositoryInterface
import com.mkhaled.movieapp.movieScreen.domain.model.MovieUIData
import io.reactivex.Flowable
import retrofit2.HttpException
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val getMovieApi: GetMovieApi,
    private val mapper: MovieMapper,
    private val cache: Cache,
) : HomeRepositoryInterface {

    override fun getAllSavedMovies(lastUpdatedTime: Long): Flowable<List<CashedItemData>> =
        cache.getAll(lastUpdatedTime).distinctUntilChanged()

    override suspend fun storeMovie(item: CashedItemData) = cache.storeMovie(item)

    override suspend fun getMoviesFromApi(): List<MovieUIData> {
        try {
            return mapper.mapToDomain(getMovieApi.getMovie())
        } catch (e: HttpException) {
            throw handleException(e)
        }
    }

    override suspend fun deleteOldMovies() = cache.deleteAll()

    private fun handleException(exception: HttpException): Exception =
    // TODO: get error schema and parse error
        // val errorResponse = convertErrorBody(exception)
        throw NetworkException(
            message = exception.message ?: "Code ${exception.code()}", code = exception.code()
        )
}


//    private fun convertErrorBody(exception: HttpException): ApiMessage? {
//        return try {
//            exception.response()?.errorBody()?.source()?.let {
//                val moshiAdapter = Moshi.Builder().build().adapter(ApiMessage::class.java)
//                moshiAdapter.fromJson(it)
//            }
//        } catch (exception: Exception) {
//            null
//        }
//    }

