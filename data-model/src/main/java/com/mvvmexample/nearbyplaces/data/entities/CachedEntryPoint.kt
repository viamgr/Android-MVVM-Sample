package com.mvvmexample.nearbyplaces.data.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "entryPoint",
    indices = [Index(value = ["entryPointId", "placeId"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = CachedPlace::class,
            parentColumns = ["id"],
            childColumns = ["placeId"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class CachedEntryPoint(
    @PrimaryKey(autoGenerate = true)
    val entryPointId: Long? = null,
    val placeId: Long,
    @Embedded(prefix = "position_")
    val position: CachedGeoLocation? = null,
    val type: String
)