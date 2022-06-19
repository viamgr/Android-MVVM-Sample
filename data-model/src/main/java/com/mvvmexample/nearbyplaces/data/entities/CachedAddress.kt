package com.mvvmexample.nearbyplaces.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class CachedAddress(
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
