package com.group.core.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out R> {
    data class Success<out R>(val data: R?) : Result<R>
    data class Error(val exception: Throwable) : Result<Nothing>
    data object Loading : Result<Nothing>
}

fun <T> Flow<NetworkResponse<T>>.asResult(): Flow<Result<T>> = map<NetworkResponse<T>, Result<T>> {
    if (it.isSuccess()) {
        Result.Success(it.data)
    } else {
        Result.Error(Throwable(it.message))
    }

}
    .onStart { emit(Result.Loading) }
    .catch { emit(Result.Error(it)) }




