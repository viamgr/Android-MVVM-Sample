package com.mvvmexample.nearbyplaces.model

data class Place(
    val id: Long,
    val dist: Double,
    val info: String,
    val position: GeoLocation? = null,
    val score: Double,
    val type: String,
    val viewport: Viewport? = null,
    val address: Address? = null,
    val poi: Poi? = null,
    val dataSources: DataSources? = null,
    val entryPoints: List<EntryPoint>? = null
)

data class Address(
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
    val streetNumber: String? = null,
)

data class DataSources(
    val poiDetails: List<PoiDetail>? = null
)

data class EntryPoint(
    val position: GeoLocation? = null,
    val type: String
)

data class Poi(
    val categories: List<String>? = null,
    val categorySet: List<CategorySet>? = null,
    val classifications: List<Classification>? = null,
    val name: String? = null,
    val phone: String? = null,
    val url: String? = ""
)

data class Viewport(
    val btmRightPoint: GeoLocation,
    val topLeftPoint: GeoLocation
)

data class PoiDetail(
    val id: String,
    val sourceName: String
)

data class CategorySet(
    val id: Int
)

data class Classification(
    val code: String,
    val names: List<Name>
)

data class Name(
    val name: String,
    val nameLocale: String
)