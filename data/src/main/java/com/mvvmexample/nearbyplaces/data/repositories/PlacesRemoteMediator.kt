package com.mvvmexample.nearbyplaces.data.repositories

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mvvmexample.nearbyplaces.data.entities.CachedPlace
import com.mvvmexample.nearbyplaces.domain.repositories.LocationRepository
import com.mvvmexample.nearbyplaces.domain.repositories.PlaceRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlacesRemoteMediator @Inject constructor(
    private val locationRepository: LocationRepository,
    private val placeRepository: PlaceRepository
) :
    RemoteMediator<Int, CachedPlace>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CachedPlace>
    ): MediatorResult {
        val loadedCount = placeRepository.getLoadedCountItems()
        if (loadType == LoadType.PREPEND) {
            return MediatorResult.Success(false)
        }
        val lastLocation = locationRepository.getLastLocation().first()!!
        return MediatorResult.Success(
            placeRepository.loadPlaces(
                lastLocation.lat,
                lastLocation.lon,
                loadedCount,
                10000,
                state.config.pageSize
            )
        )
    }
}