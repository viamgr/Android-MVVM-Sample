package com.mvvmexample.nearbyplaces.domain.usecases

import android.location.Location.distanceBetween
import com.mvvmexample.nearbyplaces.domain.common.UseCase
import com.mvvmexample.nearbyplaces.domain.repositories.LocationRepository
import com.mvvmexample.nearbyplaces.domain.repositories.PlaceRepository
import com.mvvmexample.nearbyplaces.domain.utils.IoDispatcher
import com.mvvmexample.nearbyplaces.model.GeoLocation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateLocation @Inject constructor(
    private val locationRepository: LocationRepository,
    private val placesRepository: PlaceRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : UseCase<UpdateLocation.Location, Unit>(dispatcher) {

    override suspend fun execute(parameter: Location) {
        val lastLocation = locationRepository.getLastLocation().firstOrNull()
        val reload = if (lastLocation != null) {
            val distance = FloatArray(1)
            distanceBetween(
                lastLocation.lat, lastLocation.lon, parameter.lat, parameter.lon, distance
            )
            distance.first() > 100F
        } else {
            true
        }

        if (reload) {
            locationRepository.updateLastLocation(GeoLocation(parameter.lat, parameter.lon))
//            placesRepository.trigger()
            placesRepository.clearPlaces()
        }
    }

    data class Location(val lat: Double, val lon: Double)
}