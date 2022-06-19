package com.mvvmexample.nearbyplaces.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mvvmexample.nearbyplaces.data.datasource.PlaceLocalDataSource
import com.mvvmexample.nearbyplaces.data.entities.*
import com.mvvmexample.nearbyplaces.data.model.network.*
import com.mvvmexample.nearbyplaces.data.model.network.GeoLocation
import com.mvvmexample.nearbyplaces.domain.repositories.PlaceRepository
import com.mvvmexample.nearbyplaces.model.*
import dagger.Lazy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaceRepositoryImpl @Inject constructor(
    private val placeLocalDataSource: PlaceLocalDataSource,
    private val placeRemoteDataSource: PlaceRemoteDataSource,
    private val placesRemoteMediator: Lazy<PlacesRemoteMediator>,
) : PlaceRepository {
    override fun observePagedList(
        pagingConfig: PagingConfig
    ): Flow<PagingData<Place>> {
        return Pager(
            config = pagingConfig,
            remoteMediator = placesRemoteMediator.get(),
            pagingSourceFactory = {
                placeLocalDataSource.observePagedList()
            }
        ).flow.map { pagingData ->
            pagingData.map {
                it.fromCachedEntity()
            }
        }
    }

    private fun CachedPlace.fromCachedEntity(): Place {
        return Place(
            id = id!!,
            dist = dist,
            info = info,
            score = score,
            type = type,
            poi = poi?.fromEntity(),
            viewport = viewport?.fromEntity(),
            position = position?.fromEntity(),
            address = address?.fromEntity(),
            dataSources = dataSources?.fromEntity(),
            entryPoints = null,
        )

    }

    override fun getPlace(id: Long): Flow<Place> = placeLocalDataSource.getPlace(id).map {
        it.fromCachedEntity()
    }

    override suspend fun loadPlaces(
        lat: Double,
        lon: Double,
        ofs: Int,
        radius: Int,
        limit: Int
    ): Boolean {
        val places = placeRemoteDataSource.getPlaces(lat, lon, ofs, radius, limit).also {
            cachePlaceList(it.results)
        }
        return places.summary.offset * limit >= places.summary.totalResults
    }

    override suspend fun clearPlaces() = placeLocalDataSource.clearAll()

    override suspend fun getLoadedCountItems() = placeLocalDataSource.getItemCounts()
    override suspend fun trigger() {
        placeLocalDataSource.insert(
            CachedPlace(
                dist = 0.0,
                remoteId = "",
                info = "",
                score = 0.0,
                type = ""
            )
        )
    }

    private suspend fun cachePlaceList(remotePlaces: List<RemotePlace>) {
        remotePlaces.map { remotePlace ->
            CachedPlace(
                remoteId = remotePlace.id,
                dist = remotePlace.dist,
                score = remotePlace.score,
                type = remotePlace.type,
                info = remotePlace.info,
                position = remotePlace.position?.toEntity(),
                viewport = remotePlace.viewport?.toEntity(),
                address = remotePlace.address?.toCachedAddress(),
                poi = remotePlace.poi.toEntity(),
                dataSources = remotePlace.dataSources?.toEntity(),
            ).also { cachedPlace ->
                placeLocalDataSource.insert(cachedPlace).also {
                    insertEntryPoint(it, remotePlace)

                }
            }
        }
    }

    private suspend fun insertEntryPoint(cachedPlace: CachedPlace, remotePlace: RemotePlace) {
        cachedPlace.id?.let { it ->
            remotePlace.entryPoints?.toEntity(
                it
            )?.let {
                placeLocalDataSource.insertEntryPoint(
                    it
                )
            }
        }
    }

}

private fun CachedPoi.fromEntity() = Poi(
    categories = categories,
    categorySet = categorySet?.fromCachedCategorySet(),
    classifications = classifications?.fromCachedClassification(),
    name = name,
    phone = phone,
    url = url,

    )

private fun List<CachedCategorySet>.fromCachedCategorySet() = map {
    CategorySet(it.id)
}

private fun List<CachedClassification>.fromCachedClassification(): List<Classification> = map {
    Classification(it.code, names = it.names.fromCachedName())
}


private fun List<CachedName>.fromCachedName(): List<Name> = map {
    Name(it.name, it.nameLocale)
}


private fun CachedViewport.fromEntity() = Viewport(
    btmRightPoint.fromEntity(),
    topLeftPoint.fromEntity()
)

private fun CachedGeoLocation.fromEntity() =
    com.mvvmexample.nearbyplaces.model.GeoLocation(lat, lon)

private fun CachedAddress.fromEntity() = Address(
    country = country,
    countryCode = countryCode,
    countryCodeISO3 = countryCodeISO3,
    countrySecondarySubdivision = countrySecondarySubdivision,
    countrySubdivision = countrySubdivision,
    countrySubdivisionName = countrySubdivisionName,
    extendedPostalCode = extendedPostalCode,
    freeformAddress = freeformAddress,
    localName = localName,
    municipality = municipality,
    municipalitySubdivision = municipalitySubdivision,
    postalCode = postalCode,
    streetName = streetName,
    streetNumber = streetNumber,

    )

private fun CachedDataSources.fromEntity() = DataSources(poiDetails = poiDetails?.fromEntity())

private fun List<CachedPoiDetail>.fromEntity(): List<PoiDetail> = map {
    PoiDetail(it.id, it.sourceName)
}


private fun List<RemoteEntryPoint>.toEntity(placeId: Long): List<CachedEntryPoint> {
    return map {
        CachedEntryPoint(
            placeId = placeId,
            position = it.position.toEntity(),
            type = it.type
        )
    }
}

private fun RemoteDataSources.toEntity(): CachedDataSources {
    return CachedDataSources(poiDetails?.toEntity())
}

private fun List<RemotePoiDetail>.toEntity(): List<CachedPoiDetail> = map {
    CachedPoiDetail(it.id, it.sourceName)
}

private fun RemotePoi.toEntity(): CachedPoi {
    return CachedPoi(
        categories,
        categorySet?.toCachedCategorySet(),
        classifications = classifications?.toCachedClassification(),
        name,
        phone,
        url
    )
}

private fun List<RemoteCategorySet>.toCachedCategorySet(): List<CachedCategorySet> = map {
    CachedCategorySet(it.id)
}

private fun List<RemoteClassification>.toCachedClassification(): List<CachedClassification> = map {
    CachedClassification(it.code, it.names.toCachedName())
}

private fun List<RemoteName>.toCachedName(): List<CachedName> = map {
    CachedName(it.name, it.nameLocale)
}

private fun RemoteAddress.toCachedAddress(): CachedAddress {
    return CachedAddress(
        country = country,
        countryCode = countryCode,
        countryCodeISO3 = countryCodeISO3,
        countrySecondarySubdivision = countrySecondarySubdivision,
        countrySubdivision = countrySubdivision,
        countrySubdivisionName = countrySubdivisionName,
        extendedPostalCode = extendedPostalCode,
        freeformAddress = freeformAddress,
        localName = localName,
        municipality = municipality,
        municipalitySubdivision = municipalitySubdivision,
        postalCode = postalCode,
        streetName = streetName,
        streetNumber = streetNumber,
    )
}

private fun RemoteViewport.toEntity() =
    CachedViewport(btmRightPoint.toEntity(), topLeftPoint.toEntity())

private fun GeoLocation.toEntity() = CachedGeoLocation(lat, lon)
