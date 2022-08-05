package com.example.breweri.domain.repository

import androidx.paging.PagingData
import com.example.breweri.domain.model.Brewery
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getBrewery(id: String): Brewery
    suspend fun searchBreweries(query: String): Flow<PagingData<Brewery>>
}