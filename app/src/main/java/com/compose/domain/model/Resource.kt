package com.compose.domain.model

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T?, message: String?): Resource<T>(data, message)
    class Success<T>(data: T?, message: String?): Resource<T>(data, message)
    class Error<T>(data: T?, message: String?): Resource<T>(data, message)
}