package com.mvvmexample.nearbyplaces.data.model.network

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(val results: List<T>, val summary: Summary)


