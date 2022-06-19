package com.mvvmexample.nearbyplaces.data.type_converters

import androidx.room.TypeConverter
import com.mvvmexample.nearbyplaces.data.entities.CachedPoi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PoiGsonTypeConvertor {
    @TypeConverter
    fun fromString(value: String): CachedPoi? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toString(value: CachedPoi?): String {
        return Json.encodeToString(value)
    }
}
