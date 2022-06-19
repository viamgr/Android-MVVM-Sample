package com.mvvmexample.nearbyplaces.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mvvmexample.nearbyplaces.data.entities.CachedEntryPoint
import com.mvvmexample.nearbyplaces.data.entities.CachedPlace
import com.mvvmexample.nearbyplaces.data.entities.Pref
import com.mvvmexample.nearbyplaces.data.type_converters.DataSourcesGsonTypeConvertor
import com.mvvmexample.nearbyplaces.data.type_converters.PoiGsonTypeConvertor

@TypeConverters(PoiGsonTypeConvertor::class, DataSourcesGsonTypeConvertor::class)
@Database(
    entities = [
        CachedPlace::class,
        CachedEntryPoint::class,
        Pref::class,
    ],
    version = 3,
    exportSchema = false
)
abstract class NearMeRoomDatabase : RoomDatabase(), NearMeDatabase