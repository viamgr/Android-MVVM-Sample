package com.mvvmexample.nearbyplaces.domain.usecases

import com.mvvmexample.nearbyplaces.domain.common.FlowUseCase
import com.mvvmexample.nearbyplaces.domain.repositories.LocationRepository
import com.mvvmexample.nearbyplaces.domain.utils.IoDispatcher
import com.mvvmexample.nearbyplaces.domain.utils.Resource
import com.mvvmexample.nearbyplaces.model.GeoLocation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLastLocation @Inject constructor(
    private val locationRepository: LocationRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : FlowUseCase<Unit, GeoLocation?>(dispatcher) {
    override fun execute(parameter: Unit): Flow<Resource<GeoLocation?>> {
        return locationRepository.getLastLocation().map {
            Resource.Success(it)
        }
    }
}