package com.mvvmexample.nearbyplaces.domain.repositories

import com.mvvmexample.nearbyplaces.model.GeoLocation
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun updateLastLocation(geoLocation: GeoLocation)
    fun getLastLocation(): Flow<GeoLocation?>
}