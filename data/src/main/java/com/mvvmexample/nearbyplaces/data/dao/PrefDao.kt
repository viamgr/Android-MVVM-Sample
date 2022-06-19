package com.mvvmexample.nearbyplaces.data.dao

import androidx.room.*
import com.mvvmexample.nearbyplaces.data.entities.Pref
import kotlinx.coroutines.flow.Flow

@Dao
interface PrefDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pref: Pref)

    @Transaction
    @Query("SELECT * FROM pref WHERE `key`= :key LIMIT 1")
    fun select(key: String): Flow<Pref?>
}