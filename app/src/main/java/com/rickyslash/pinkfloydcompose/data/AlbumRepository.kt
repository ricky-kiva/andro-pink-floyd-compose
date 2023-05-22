package com.rickyslash.pinkfloydcompose.data

import com.rickyslash.pinkfloydcompose.model.Album
import com.rickyslash.pinkfloydcompose.model.AlbumsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AlbumRepository {
    private val albums = mutableListOf<Album>()

    init {
        if (albums.isEmpty()) {
            AlbumsDataSource.albumsData.forEach {
                albums.add(it)
            }
        }
    }

    fun getAllAlbums(): Flow<List<Album>> {
        return flowOf(albums)
    }

    fun getAlbumById(albumId: Long): Album = albums.first { it.id == albumId }

    companion object {
        @Volatile
        private var instance: AlbumRepository? = null

        fun getInstance(): AlbumRepository = instance ?: synchronized(this) {
            AlbumRepository().apply { instance = this }
        }
    }

}