package com.mkhaled.movieapp.core.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.mkhaled.movieapp.core.presentation.AppNavHost
import com.mkhaled.movieapp.core.presentation.ui.theme.MovieAppTheme
import com.mkhaled.movieapp.movieScreen.presentation.InvalidateMoviesCacheWorker
import com.mkhaled.movieapp.movieScreen.presentation.MovieScreen
import com.mkhaled.movieapp.movieScreen.presentation.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost()
                }
            }
        }
        scheduleInvalidateMoviesCache()
    }


    private fun scheduleInvalidateMoviesCache() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicWorkRequest = PeriodicWorkRequest.Builder(
            InvalidateMoviesCacheWorker::class.java,
            15, // Repeat every 4 hours
            TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
    }
}