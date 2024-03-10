package com.mkhaled.movieapp.core.domain.model

import java.io.IOException


class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)

class NetworkException(message: String, val code: Int? = null): Exception(message)
