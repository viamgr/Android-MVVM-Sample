package com.mvvmexample.nearbyplaces.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.mvvmexample.nearbyplaces.data.entities.CachedEntryPoint

@Dao
interface EntryPointDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg cachedEntryPoint: CachedEntryPoint)

}