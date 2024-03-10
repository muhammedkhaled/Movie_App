package com.mkhaled.movieapp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.mkhaled.movieapp.core.utils.Logger
import com.mkhaled.movieapp.movieScreen.domain.usecase.DeleteOldMovies
import com.mkhaled.movieapp.movieScreen.presentation.InvalidateMoviesCacheWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var customWorkerFactory: CustomWorkerFactory

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() {
        Logger.init()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(customWorkerFactory).build()
}

class CustomWorkerFactory @Inject constructor(
    private val deleteOldMovies: DeleteOldMovies
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return InvalidateMoviesCacheWorker(deleteOldMovies, appContext, workerParameters)
    }
}