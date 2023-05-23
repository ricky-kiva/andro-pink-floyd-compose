package com.rickyslash.pinkfloydcompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllAlbums() {
        viewModelScope.launch {
            repository.getAllAlbums()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { _uiState.value = UiState.Success(it) }
        }
    }

    fun search(newQuery: String) {
        viewModelScope.launch {
            _query.value = newQuery
            repository.searchAlbum(_query.value).collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}