package com.example.breweri.di

import androidx.paging.ExperimentalPagingApi
import com.example.breweri.BuildConfig
import com.example.breweri.data.remote.OpenBreweryDB
import com.example.breweri.data.repository.RemoteDataSourceImpl
import com.example.breweri.domain.repository.RemoteDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val jsonConverter = Json {
            ignoreUnknownKeys = true
        }.asConverterFactory(contentType = contentType)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_ROOT)
            .client(okHttpClient)
            .addConverterFactory(jsonConverter)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): OpenBreweryDB {
        return retrofit.create(OpenBreweryDB::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        api: OpenBreweryDB
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            openBreweryDB = api
        )
    }
}