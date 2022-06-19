package com.mvvmexample.nearbyplaces.data.model.network

import kotlinx.serialization.Serializable

@Serializable
data class RemotePlace(
    val address: RemoteAddress? = null,
    val dataSources: RemoteDataSources? = null,
    val dist: Double,
    val entryPoints: List<RemoteEntryPoint>? = null,
    val id: String,
    val info: String,
    val poi: RemotePoi,
    val position: GeoLocation? = null,
    val score: Double,
    val type: String,
    val viewport: RemoteViewport? = null
)

@Serializable
data class RemoteAddress(
    val country: String? = null,
    val countryCode: String? = null,
    val countryCodeISO3: String? = null,
    val countrySecondarySubdivision: String? = null,
    val countrySubdivision: String? = null,
    val countrySubdivisionName: String? = null,
    val extendedPostalCode: String? = null,
    val freeformAddress: String? = null,
    val localName: String? = null,
    val municipality: String? = null,
    val municipalitySubdivision: String? = null,
    val postalCode: String? = null,
    val streetName: String? = null,
    val streetNumber: String? = null
)

@Serializable
data class RemoteDataSources(
    val poiDetails: List<RemotePoiDetail>? = null
)

@Serializable
data class RemoteEntryPoint(
    val position: GeoLocation,
    val type: String
)

@Serializable
data class RemotePoi(
    val categories: List<String>? = null,
    val categorySet: List<RemoteCategorySet>? = null,
    val classifications: List<RemoteClassification>? = null,
    val name: String? = null,
    val phone: String? = null,
    val url: String? = ""
)

@Serializable
data class RemoteViewport(
    val btmRightPoint: GeoLocation,
    val topLeftPoint: GeoLocation
)

@Serializable
data class RemotePoiDetail(
    val id: String,
    val sourceName: String
)

@Serializable
data class RemoteCategorySet(
    val id: Int
)

@Serializable
data class RemoteClassification(
    val code: String,
    val names: List<RemoteName>
)

@Serializable
data class RemoteName(
    val name: String,
    val nameLocale: String
)