package com.mvvmexample.nearbyplaces.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "place", indices = [Index(value = ["remoteId"], unique = true)]
)
data class CachedPlace(
    @PrimaryKey var id: Long? = null,
    val remoteId: String,
    val dist: Double,
    val info: String,
    val score: Double,
    val type: String,
    val poi: CachedPoi? = null,
    val dataSources: CachedDataSources? = null,
    @Embedded(prefix = "address_") val address: CachedAddress? = null,
    @Embedded(prefix = "position_") val position: CachedGeoLocation? = null,
    @Embedded(prefix = "viewport_") val viewport: CachedViewport? = null,
)