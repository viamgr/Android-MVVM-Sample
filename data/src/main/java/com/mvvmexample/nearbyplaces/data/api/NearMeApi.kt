package com.mvvmexample.nearbyplaces.data.api

import com.mvvmexample.nearbyplaces.data.model.network.BaseResponse
import com.mvvmexample.nearbyplaces.data.model.network.RemotePlace
import retrofit2.http.GET
import retrofit2.http.Query

interface NearMeApi {

    @GET("/search/2/nearbySearch/.json?key=nt0WpoWFNPf7PxPWE2qcHgjtthK73CPY")
    suspend fun getPlaces(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("ofs") ofs: Int,
        @Query("radius") radius: Int? = 100,
        @Query("limit") limit: Int? = 10,
    ): BaseResponse<RemotePlace>
}