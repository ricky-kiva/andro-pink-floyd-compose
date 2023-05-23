package com.rickyslash.pinkfloydcompose.ui.screen.albumdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickyslash.pinkfloydcompose.data.AlbumRepository
import com.rickyslash.pinkfloydcompose.model.Album
import com.rickyslash.pinkfloydcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AlbumDetailViewModel(private val repository: AlbumRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Album>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Album>> get() = _uiState

    private val _favState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val favState: StateFlow<Boolean> get() = _favState

    fun getAlbumById(albumId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getAlbumById(albumId))
        }
    }

    fun updateFav(albumId: Long) {
        viewModelScope.launch {
            repository.updateFav(albumId)
            renewFavState(albumId)
        }
    }

    fun renewFavState(albumId: Long) {
        _favState.value = repository.getAlbumById(albumId).fav
    }
}