package com.mvvmexample.nearbyplaces.domain.usecases

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mvvmexample.nearbyplaces.domain.repositories.PlaceRepository
import com.mvvmexample.nearbyplaces.model.Place
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPagedPlaceListUseCase @Inject constructor(
    private val placeRepository: PlaceRepository,
) {
    operator fun invoke(): Flow<PagingData<Place>> {
        val pagingConfig = PagingConfig(
            pageSize = 15,
            prefetchDistance = 3,
            enablePlaceholders = true,
            initialLoadSize = 15
        )
        return placeRepository.observePagedList(pagingConfig).catch {
            // TODO: 1/30/2022 UnHandled Cache
        }
    }
}