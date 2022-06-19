package com.mvvmexample.nearbyplaces.data.datasource

import com.mvvmexample.nearbyplaces.data.entities.Pref
import kotlinx.coroutines.flow.Flow

interface PrefLocalDataSource {
    suspend fun insert(pref: Pref)
    fun select(key: String): Flow<Pref?>
}