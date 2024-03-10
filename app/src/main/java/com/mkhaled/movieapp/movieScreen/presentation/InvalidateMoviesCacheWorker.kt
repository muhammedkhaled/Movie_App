package com.mkhaled.movieapp.movieScreen.presentation

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.mkhaled.movieapp.movieScreen.domain.usecase.DeleteOldMovies
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@HiltWorker
class InvalidateMoviesCacheWorker @AssistedInject constructor(
    @Assisted private val deleteOldMovies: DeleteOldMovies,
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        // Delete all movies to invalidate cache
        deleteOldMovies()
        return Result.success()
    }

}


