package com.mvvmexample.nearbyplaces.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class CachedGeoLocation(
    val lat: Double,
    val lon: Double
)