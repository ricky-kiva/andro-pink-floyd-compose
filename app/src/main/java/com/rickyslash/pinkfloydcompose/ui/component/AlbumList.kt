package com.rickyslash.pinkfloydcompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.rickyslash.pinkfloydcompose.model.Album

@Composable
fun AlbumList(modifier: Modifier = Modifier, albumList: List<Album>, navigateToDetail: (Long) -> Unit) {
    LazyColumn(
        modifier = modifier
            .testTag("AlbumList")
    ) {
        items(albumList) { data ->
            AlbumListItem(
                imageUrl = data.imageUrl,
                title = data.title,
                release = data.release,
                modifier = Modifier
                    .clickable { navigateToDetail(data.id) }
            )
        }
    }
}