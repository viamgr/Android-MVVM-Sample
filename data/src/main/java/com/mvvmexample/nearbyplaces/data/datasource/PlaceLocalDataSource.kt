package com.mvvmexample.nearbyplaces.data.datasource

import androidx.paging.PagingSource
import com.mvvmexample.nearbyplaces.data.entities.CachedEntryPoint
import com.mvvmexample.nearbyplaces.data.entities.CachedPlace
import kotlinx.coroutines.flow.Flow

interface PlaceLocalDataSource {
    suspend fun insert(cachedPlace: CachedPlace): CachedPlace

    suspend fun insertEntryPoint(entryPoints: List<CachedEntryPoint>)

    fun observePagedList(): PagingSource<Int, CachedPlace>

    suspend fun clearAll()
    suspend fun getItemCounts(): Int
    fun getPlace(id: Long): Flow<CachedPlace>
}