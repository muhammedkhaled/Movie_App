package com.mkhaled.movieapp.core.data.api.interceptors

import com.mkhaled.movieapp.core.utils.Logger
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {

  override fun log(message: String) {
    Logger.i(message)
  }
}