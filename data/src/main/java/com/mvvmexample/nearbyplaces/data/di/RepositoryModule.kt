package com.mvvmexample.nearbyplaces.data.di

import com.mvvmexample.nearbyplaces.data.repositories.LocationRepositoryImpl
import com.mvvmexample.nearbyplaces.data.repositories.PlaceRepositoryImpl
import com.mvvmexample.nearbyplaces.domain.repositories.LocationRepository
import com.mvvmexample.nearbyplaces.domain.repositories.PlaceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsPlaceRepository(impl: PlaceRepositoryImpl): PlaceRepository

    @Binds
    @Singleton
    abstract fun bindsLocationRepositoryImpl(impl: LocationRepositoryImpl): LocationRepository

}