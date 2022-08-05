package com.example.breweri.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breweri.data.repository.Repository
import com.example.breweri.domain.model.Brewery
import com.example.breweri.util.Constants.DETAIL_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedBrewery: MutableStateFlow<Brewery?> = MutableStateFlow(null)
    val selectedBrewery: StateFlow<Brewery?> = _selectedBrewery

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val breweryId = savedStateHandle.get<String>(DETAIL_ARGUMENT_KEY)
            _selectedBrewery.value = breweryId?.let { repository.getBrewery(id = breweryId) }
        }
    }
}