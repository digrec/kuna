package com.digrec.kuna.core.domain.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


/**
 * A generic class that holds a value.
 *
 * Created by Dejan Igrec
 */
sealed interface Result<out T> {

    data class Success<T>(val data: T) : Result<T>

    data class Error(val exception: Throwable? = null) : Result<Nothing>
}

/**
 * `true` if [Result] is of type [Result.Success].
 */
val Result<*>.succeeded
    get() = this is Result.Success

/**
 * Maps a flow to a flow of [Result].
 */
fun <T> Flow<T>.toResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> {
            Result.Success(it)
        }
        .catch {
            emit(Result.Error(it))
        }
}
