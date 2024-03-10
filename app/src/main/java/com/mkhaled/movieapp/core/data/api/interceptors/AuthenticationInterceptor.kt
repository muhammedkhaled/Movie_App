package com.mkhaled.movieapp.core.data.api.interceptors


import com.mkhaled.movieapp.BuildConfig
import okhttp3.*
import javax.inject.Inject

class AuthenticationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val interceptedRequest: Request = chain.createAuthenticatedRequest()
        return chain.proceed(interceptedRequest)
    }

    private fun Interceptor.Chain.createAuthenticatedRequest(): Request {
        return request()
            .newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "bearer ${BuildConfig.TOKEN}")
            .build()
    }

}
