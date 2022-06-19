package com.mvvmexample.nearbyplaces.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class CachedPoi(
    val categories: List<String>? = null,
    val categorySet: List<CachedCategorySet>? = null,
    val classifications: List<CachedClassification>? = null,
    val name: String? = null,
    val phone: String? = null,
    val url: String? = ""
)