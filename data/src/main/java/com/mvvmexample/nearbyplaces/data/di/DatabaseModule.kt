package com.mvvmexample.nearbyplaces.data.di

import android.content.Context
import androidx.room.Room
import com.mvvmexample.nearbyplaces.data.database.NearMeDatabase
import com.mvvmexample.nearbyplaces.data.database.NearMeRoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Singleton
    @Binds
    abstract fun bindsNearbyDatabase(db: NearMeRoomDatabase): NearMeDatabase

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context,
        ): NearMeRoomDatabase {
            return Room.databaseBuilder(context, NearMeRoomDatabase::class.java, "nearby-db")
                .enableMultiInstanceInvalidation()
                .build()
        }

        @Provides
        @Singleton
        fun providePlaceDao(db: NearMeRoomDatabase) = db.placeDao()

        @Provides
        @Singleton
        fun provideEntryPointDao(db: NearMeRoomDatabase) = db.entryPintDao()

        @Provides
        @Singleton
        fun providePrefDao(db: NearMeRoomDatabase) = db.prefDao()

    }
}