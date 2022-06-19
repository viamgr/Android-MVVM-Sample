package com.mvvmexample.nearbyplaces.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.mvvmexample.nearbyplaces.data.entities.CachedPlace
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(place: CachedPlace): Long

    @Transaction
    @Query("SELECT * FROM place ORDER BY score DESC")
    fun observePagedList(): PagingSource<Int, CachedPlace>

    @Query("SELECT * FROM place WHERE id=:id")
    fun getPlace(id: Long): Flow<CachedPlace>

    @Transaction
    @Query("DELETE FROM place")
    fun clearAll()

    @Query("SELECT count(id) FROM place")
    suspend fun getItemCounts(): Int
}