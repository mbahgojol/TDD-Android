package com.blank.chapter10.utils.api

sealed class ResultState {
    object Loading : ResultState()

    data class Success<T>(val data: T, val message: String) : ResultState()

    data class Error(val throwable: Throwable) : ResultState()

    data class NoConnection(val throwable: Throwable) : ResultState()

    data class BadRequest(val throwable: Throwable) : ResultState()

    data class NotFound(val throwable: Throwable) : ResultState()

    data class Unauthorized(val throwable: Throwable) : ResultState()

    data class Conflict(val throwable: Throwable) : ResultState()

    data class Forbidden(val throwable: Throwable) : ResultState()
}