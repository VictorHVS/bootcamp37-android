package com.example.breweri.presentation.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.breweri.data.repository.Repository
import com.example.breweri.domain.model.Brewery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedBreweries = MutableStateFlow<PagingData<Brewery>>(PagingData.empty())
    val searchedBreweries = _searchedBreweries

    init {
        _searchQuery.value = "san_diego"
        searchBreweries(_searchQuery.value)
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchBreweries(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchBreweries(query = query).cachedIn(viewModelScope).collect {
                _searchedBreweries.value = it
            }
        }
    }
}