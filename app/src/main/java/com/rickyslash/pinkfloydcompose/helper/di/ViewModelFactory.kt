package com.rickyslash.pinkfloydcompose.helper.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rickyslash.pinkfloydcompose.data.AlbumRepository
import com.rickyslash.pinkfloydcompose.ui.screen.albumdetail.AlbumDetailViewModel
import com.rickyslash.pinkfloydcompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: AlbumRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) {
            return AlbumDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}