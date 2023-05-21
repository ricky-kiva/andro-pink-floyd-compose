package com.rickyslash.pinkfloydcompose.helper.di

import com.rickyslash.pinkfloydcompose.data.AlbumRepository

object Injection {
    fun provideRepository(): AlbumRepository {
        return AlbumRepository.getInstance()
    }
}