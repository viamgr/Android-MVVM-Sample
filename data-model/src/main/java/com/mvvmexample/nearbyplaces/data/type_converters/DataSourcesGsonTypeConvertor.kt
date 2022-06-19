package com.mvvmexample.nearbyplaces.data.type_converters

import androidx.room.TypeConverter
import com.mvvmexample.nearbyplaces.data.entities.CachedDataSources
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DataSourcesGsonTypeConvertor {
    @TypeConverter
    fun fromString(value: String): CachedDataSources? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toString(value: CachedDataSources?): String {
        return Json.encodeToString(value)
    }
}
