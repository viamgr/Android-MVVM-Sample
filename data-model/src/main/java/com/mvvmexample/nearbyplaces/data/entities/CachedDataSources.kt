package com.mvvmexample.nearbyplaces.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class CachedDataSources(
    val poiDetails: List<CachedPoiDetail>? = null
)

@Serializable
data class CachedPoiDetail(
    val id: String,
    val sourceName: String
)
