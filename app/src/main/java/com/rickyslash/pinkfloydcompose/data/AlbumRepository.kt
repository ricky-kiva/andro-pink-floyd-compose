package com.rickyslash.pinkfloydcompose.data

import com.rickyslash.pinkfloydcompose.model.Album
import com.rickyslash.pinkfloydcompose.model.AlbumsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

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

    fun updateFav(albumId: Long) {
        val index = albums.indexOfFirst { it.id == albumId }
        if (index >= 0) {
            val album = albums[index]
            albums[index] = album.copy(
                id = album.id,
                title =  album.title,
                release = album.release,
                song = album.song,
                imageUrl = album.imageUrl,
                desc = album.desc,
                fav = !(album.fav)
            )
        }
    }

    fun getFavAlbums(): Flow<List<Album>> {
        return getAllAlbums().map { albums ->
            albums.filter { it.fav }
        }
    }

    companion object {
        @Volatile
        private var instance: AlbumRepository? = null

        fun getInstance(): AlbumRepository = instance ?: synchronized(this) {
            AlbumRepository().apply { instance = this }
        }
    }

}