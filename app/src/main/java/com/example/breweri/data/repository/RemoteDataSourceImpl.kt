package com.example.breweri.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.breweri.data.paging_source.SearchBrewerySource
import com.example.breweri.data.remote.OpenBreweryDB
import com.example.breweri.domain.model.Brewery
import com.example.breweri.domain.repository.RemoteDataSource
import com.example.breweri.util.Constants.BREWERIES_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val openBreweryDB: OpenBreweryDB
) : RemoteDataSource {

    override suspend fun getBrewery(id: String): Brewery {
        return openBreweryDB.getBrewery(id)
    }

    override suspend fun searchBreweries(query: String): Flow<PagingData<Brewery>> {
        return Pager(
            config = PagingConfig(pageSize = BREWERIES_PER_PAGE),
            pagingSourceFactory = {
                SearchBrewerySource(
                    api = openBreweryDB,
                    query = query
                )
            }
        ).flow
    }


}