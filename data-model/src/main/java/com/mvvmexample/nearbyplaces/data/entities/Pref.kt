package com.mvvmexample.nearbyplaces.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "pref",
)
data class Pref(
    @PrimaryKey
    val key: String,
    val lat: Double,
    val lon: Double,
)