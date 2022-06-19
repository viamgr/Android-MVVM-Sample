package com.mvvmexample.nearbyplaces.data.datasource

import com.mvvmexample.nearbyplaces.data.api.NearMeApi
import com.mvvmexample.nearbyplaces.data.model.network.BaseResponse
import com.mvvmexample.nearbyplaces.data.model.network.RemotePlace
import com.mvvmexample.nearbyplaces.data.repositories.PlaceRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaceRemoteDataSourceImpl @Inject constructor(
    private val nearMeApi: NearMeApi,
) : PlaceRemoteDataSource {
    override suspend fun getPlaces(
        lat: Double,
        lon: Double,
        ofs: Int,
        radius: Int,
        limit: Int
    ): BaseResponse<RemotePlace> = nearMeApi.getPlaces(lat, lon, ofs, radius, limit)

}