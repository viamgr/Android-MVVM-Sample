package com.mvvmexample.nearbyplaces.domain.usecases

import com.mvvmexample.nearbyplaces.domain.common.FlowUseCase
import com.mvvmexample.nearbyplaces.domain.repositories.PlaceRepository
import com.mvvmexample.nearbyplaces.domain.utils.IoDispatcher
import com.mvvmexample.nearbyplaces.domain.utils.Resource
import com.mvvmexample.nearbyplaces.model.Place
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPlaceUseCase @Inject constructor(
    private val placeRepository: PlaceRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : FlowUseCase<Long, Place>(dispatcher) {
    override fun execute(parameter: Long): Flow<Resource<Place>> {
        return placeRepository.getPlace(parameter).map {
            Resource.Success(it)
        }
    }
}