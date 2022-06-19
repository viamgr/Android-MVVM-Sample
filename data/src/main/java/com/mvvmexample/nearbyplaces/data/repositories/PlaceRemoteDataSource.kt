package com.mvvmexample.nearbyplaces.data.repositories

import com.mvvmexample.nearbyplaces.data.model.network.BaseResponse
import com.mvvmexample.nearbyplaces.data.model.network.RemotePlace

interface PlaceRemoteDataSource {
    suspend fun getPlaces(
        lat: Double,
        lon: Double,
        ofs: Int,
        radius: Int,
        limit: Int
    ): BaseResponse<RemotePlace>
}