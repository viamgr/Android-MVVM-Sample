package com.mvvmexample.nearbyplaces.domain.common

import com.mvvmexample.nearbyplaces.domain.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    protected abstract fun execute(parameter: P): Flow<Resource<R>>
    operator fun invoke(parameter: P): Flow<Resource<R>> = execute(parameter)
        .catch { e ->
            e.printStackTrace()
            emit(Resource.Error(e as Exception))
        }
        .flowOn(coroutineDispatcher)
}