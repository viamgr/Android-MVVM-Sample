package com.mvvmexample.nearbyplaces.domain.repositories

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mvvmexample.nearbyplaces.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {

    fun observePagedList(
        pagingConfig: PagingConfig,
    ): Flow<PagingData<Place>>

    suspend fun loadPlaces(
        lat: Double,
        lon: Double,
        ofs: Int,
        radius: Int,
        limit: Int
    ): Boolean

    suspend fun clearPlaces()

    suspend fun getLoadedCountItems(): Int
    suspend fun trigger()
    fun getPlace(id: Long): Flow<Place>
}