package com.paytm.foodium.data.remote.model

sealed class Response<out T : Any> {
    data class Success<out T : Any>(val output: T) : Response<T>()
    data class Error(val exception: Exception) : Response<Nothing>()
}
