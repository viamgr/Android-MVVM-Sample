package com.mvvmexample.nearbyplaces.data.model.network

import kotlinx.serialization.Serializable

@Serializable
data class Summary(
    val fuzzyLevel: Int,
    val geoBias: GeoLocation,
    val numResults: Int,
    val offset: Int,
    val queryTime: Int,
    val queryType: String,
    val totalResults: Int
)