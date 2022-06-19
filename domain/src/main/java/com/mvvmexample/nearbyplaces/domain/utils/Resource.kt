package com.mvvmexample.nearbyplaces.domain.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val error: Exception) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    object Canceled : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
            Loading -> "Loading"
            Canceled -> "Canceled"
        }
    }
}

inline fun <T> Resource<T>.withResource(
    onLoading: (Boolean) -> Unit,
    onSuccess: (T) -> Unit,
    onFailure: (Throwable) -> Unit
) {
    when (this) {
        Resource.Loading -> onLoading(true)
        Resource.Canceled -> onLoading(false)
        is Resource.Error -> {
            onLoading(false)
            onFailure(error)
        }
        is Resource.Success -> {
            onLoading(false)
            onSuccess(data)
        }
    }
}

/**
 * [Success.data] if [Resource] is of type [Success]
 */
fun <T> Resource<T>.successOr(fallback: T): T {
    return (this as? Resource.Success<T>)?.data ?: fallback
}

fun <T> Resource<T>.isLoading() = this is Resource.Loading

fun <T> Resource<T>.isSuccess() = this is Resource.Success

fun <T> Resource<T>.isError() = this is Error

inline fun <R> Resource<R>.onSuccess(action: (R) -> Unit): Resource<R> {
    if (this is Resource.Success) {
        action(this.data)
    }
    return this
}

inline fun <R> Resource<R>.onLoading(action: () -> Unit): Resource<R> {
    if (this is Resource.Loading) {
        action()
    }
    return this
}

inline fun <T> Resource<T>.onError(onFailure: (Exception) -> Unit): Resource<T> {
    if (this is Resource.Error) onFailure(error)
    return this
}


fun <T> Resource<T>?.dataOrNull(): T? {
    return if (this is Resource.Success) this.data else null
}

fun <T> Resource<T>?.requireData(): T {
    return (this as Resource.Success).data
}