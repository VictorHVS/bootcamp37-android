package com.example.breweri.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.breweri.data.remote.OpenBreweryDB
import com.example.breweri.domain.model.Brewery

class SearchBrewerySource(
    private val api: OpenBreweryDB,
    private val query: String
) : PagingSource<Int, Brewery>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Brewery> {
        return try {
            val apiResponse = api.searchBreweries(query = query, page = params.key ?: 1)
            if (apiResponse.isNotEmpty()) {
                LoadResult.Page(
                    data = apiResponse,
                    prevKey = if (params.key == 1) null else params.key?.minus(1),
                    nextKey = params.key?.plus(1) ?: 2
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Brewery>): Int? {
        return state.anchorPosition
    }
}