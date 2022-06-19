package com.mvvmexample.nearbyplaces.domain.common

import com.mvvmexample.nearbyplaces.domain.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameter: P): Resource<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameter).let {
                    Resource.Success(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameter: P): R
}