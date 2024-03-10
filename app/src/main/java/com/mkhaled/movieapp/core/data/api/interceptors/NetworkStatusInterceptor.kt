package com.mkhaled.movieapp.core.data.api.interceptors

import com.mkhaled.movieapp.core.utils.ConnectionManager
import com.mkhaled.movieapp.core.domain.model.NetworkUnavailableException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkStatusInterceptor @Inject constructor(
    private val connectionManager: ConnectionManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
      return if (connectionManager.isConnected) {
        chain.proceed(chain.request())
      } else {
          throw NetworkUnavailableException()
      }
    }
}
