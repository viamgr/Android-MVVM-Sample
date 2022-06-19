package com.mvvmexample.nearbyplaces.data.repositories

import com.mvvmexample.nearbyplaces.data.datasource.PrefLocalDataSource
import com.mvvmexample.nearbyplaces.data.entities.Pref
import com.mvvmexample.nearbyplaces.domain.repositories.LocationRepository
import com.mvvmexample.nearbyplaces.model.GeoLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

const val LAST_USER_LOCATION = "LAST_USER_LOCATION"

@Singleton
class LocationRepositoryImpl @Inject constructor(
    private val prefLocalDataSource: PrefLocalDataSource
) : LocationRepository {
    override suspend fun updateLastLocation(geoLocation: GeoLocation) {
        prefLocalDataSource.insert(Pref(LAST_USER_LOCATION, geoLocation.lat, geoLocation.lon))
    }

    override fun getLastLocation(): Flow<GeoLocation?> {
        val select = prefLocalDataSource.select(LAST_USER_LOCATION)
        return select.map {
            it?.let {
                GeoLocation(it.lat, it.lon)
            }
        }
    }

}