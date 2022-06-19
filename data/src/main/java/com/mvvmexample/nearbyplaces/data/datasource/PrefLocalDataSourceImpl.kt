package com.mvvmexample.nearbyplaces.data.datasource

import com.mvvmexample.nearbyplaces.data.dao.PrefDao
import com.mvvmexample.nearbyplaces.data.entities.Pref
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefLocalDataSourceImpl @Inject constructor(
    private val prefDao: PrefDao
) : PrefLocalDataSource {
    override suspend fun insert(pref: Pref) = prefDao.insert(pref)
    override fun select(key: String) = prefDao.select(key)
}