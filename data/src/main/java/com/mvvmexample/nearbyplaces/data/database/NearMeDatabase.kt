package com.mvvmexample.nearbyplaces.data.database

import com.mvvmexample.nearbyplaces.data.dao.EntryPointDao
import com.mvvmexample.nearbyplaces.data.dao.PlaceDao
import com.mvvmexample.nearbyplaces.data.dao.PrefDao

interface NearMeDatabase {
    fun placeDao(): PlaceDao
    fun entryPintDao(): EntryPointDao
    fun prefDao(): PrefDao
}