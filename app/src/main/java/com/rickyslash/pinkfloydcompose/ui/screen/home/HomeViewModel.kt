package com.rickyslash.pinkfloydcompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickyslash.pinkfloydcompose.data.AlbumRepository
import com.rickyslash.pinkfloydcompose.model.Album
import com.rickyslash.pinkfloydcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: AlbumRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Album>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Album>>> get() = _uiState

    fun getAllAlbums() {
        viewModelScope.launch {
            repository.getAllAlbums()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { _uiState.value = UiState.Success(it) }
        }
    }
}