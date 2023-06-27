package com.ruchanokal.cryptoapp.util

sealed class Resource<T>(
    val data: T? = null, val message: String? = null, val networkError: Boolean = false
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(networkError: Boolean, message: String?) :
        Resource<T>(null,message,networkError)
}