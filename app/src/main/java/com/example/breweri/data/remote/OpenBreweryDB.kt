package com.example.breweri.data.remote

import com.example.breweri.domain.model.Brewery
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenBreweryDB {

    @GET("breweries")
    suspend fun searchBreweries(
        @Query("by_city") query: String,
        @Query("page") page: Int = 1
    ): List<Brewery>

    @GET("breweries/{id}")
    suspend fun getBrewery(
        @Path("id") id: String
    ): Brewery
}