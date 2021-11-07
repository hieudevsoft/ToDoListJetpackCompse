package com.devapp.to_docompose.util

sealed class RequestState<out T>{
    object Idle: RequestState<Nothing>()
    object Loading: RequestState<Nothing>()
    data class Error(val error:Throwable): RequestState<Nothing>()
    data class Success<T>(val data:T): RequestState<T>()
}
