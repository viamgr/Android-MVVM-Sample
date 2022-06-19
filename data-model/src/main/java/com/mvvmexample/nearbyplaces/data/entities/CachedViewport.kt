package com.mvvmexample.nearbyplaces.data.entities

import androidx.room.Embedded

data class CachedViewport(
    @Embedded(prefix = "btmRightPoint_")
    val btmRightPoint: CachedGeoLocation,
    @Embedded(prefix = "topLeftPoint_")
    val topLeftPoint: CachedGeoLocation
)
