package com.mvvmexample.nearbyplaces.data.di

import com.mvvmexample.nearbyplaces.data.datasource.*
import com.mvvmexample.nearbyplaces.data.repositories.PlaceRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    @Singleton
    abstract fun bindsPlaceLocalDataSource(impl: PlaceLocalDataSourceImpl): PlaceLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsPrefLocalDataSource(impl: PrefLocalDataSourceImpl): PrefLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsPlaceRemoteDataSource(impl: PlaceRemoteDataSourceImpl): PlaceRemoteDataSource

}