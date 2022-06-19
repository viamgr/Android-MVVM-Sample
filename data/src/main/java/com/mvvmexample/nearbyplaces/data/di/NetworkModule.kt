package com.mvvmexample.nearbyplaces.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mvvmexample.nearbyplaces.data.api.NearMeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authenticateInterceptor: AuthenticateInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authenticateInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    @ExperimentalSerializationApi
    fun provideSerializableFactory() = json.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.tomtom.com/")
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideNearMeApi(retrofit: Retrofit): NearMeApi {
        return retrofit.create(NearMeApi::class.java)
    }

}