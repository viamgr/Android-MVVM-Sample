package com.mvvmexample.nearbyplaces.data.datasource

import androidx.paging.PagingSource
import com.mvvmexample.nearbyplaces.data.dao.EntryPointDao
import com.mvvmexample.nearbyplaces.data.dao.PlaceDao
import com.mvvmexample.nearbyplaces.data.entities.CachedEntryPoint
import com.mvvmexample.nearbyplaces.data.entities.CachedPlace
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaceLocalDataSourceImpl @Inject constructor(
    private val placeDao: PlaceDao,
    private val entryPointDao: EntryPointDao
) : PlaceLocalDataSource {
    override suspend fun insert(cachedPlace: CachedPlace): CachedPlace {
        val placeId = placeDao.insert(cachedPlace)
        return cachedPlace.apply {
            id = placeId
        }
    }

    override suspend fun insertEntryPoint(entryPoints: List<CachedEntryPoint>) {
        entryPointDao.insert(*entryPoints.toTypedArray())
    }

    override fun observePagedList(): PagingSource<Int, CachedPlace> = placeDao.observePagedList()

    override fun getPlace(id: Long): Flow<CachedPlace> = placeDao.getPlace(id)

    override suspend fun clearAll() = placeDao.clearAll()

    override suspend fun getItemCounts() = placeDao.getItemCounts()
}