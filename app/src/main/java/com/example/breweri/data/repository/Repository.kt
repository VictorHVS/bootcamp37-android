package com.example.breweri.data.repository

import androidx.paging.PagingData
import com.example.breweri.domain.model.Brewery
import com.example.breweri.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource
) {

    suspend fun getBrewery(id: String): Brewery {
        return remote.getBrewery(id)
    }

    suspend fun searchBreweries(query: String): Flow<PagingData<Brewery>> {
        return remote.searchBreweries(query = query)
    }
}