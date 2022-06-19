package com.mvvmexample.nearbyplaces.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class CachedClassification(
    val code: String,
    val names: List<CachedName>
)
