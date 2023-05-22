package com.rickyslash.pinkfloydcompose.ui.screen.albumdetail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.rickyslash.pinkfloydcompose.helper.di.Injection
import com.rickyslash.pinkfloydcompose.helper.di.ViewModelFactory
import com.rickyslash.pinkfloydcompose.ui.common.UiState
import com.rickyslash.pinkfloydcompose.R
import com.rickyslash.pinkfloydcompose.model.AlbumsDataSource
import com.rickyslash.pinkfloydcompose.ui.theme.PinkFloydComposeTheme

@Composable
fun AlbumDetailScreen(
    albumId: Long,
    viewModel: AlbumDetailViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    favCallback: () -> Unit,
    navigateBack: () -> Unit
) {
    Log.d("AlbumDetailScreen","Before ViewModel")
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                Log.d("AlbumDetailScreen","Loading Data on Album Detail")
                viewModel.getAlbumById(albumId)
            }
            is UiState.Success -> {
                val data = uiState.data
                Column(
                    modifier = Modifier
                        .padding(horizontal = 32.dp, vertical = 32.dp)
                ) {
                    Column(modifier = Modifier.padding(bottom = 27.dp)) {
                        AlbumDetailTopBar(
                            favCallback = favCallback,
                            navigateBack = navigateBack
                        )
                    }
                    AlbumDetailContent(
                        id = data.id,
                        title = data.title,
                        release = data.release,
                        song = data.song,
                        imageUrl = data.imageUrl,
                        desc = data.desc
                    )
                }
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun AlbumDetailContent(
    id: Long,
    title: String,
    release: String,
    song: String,
    imageUrl: String,
    desc: String
) {
    Box {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(17.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.onSecondary)
                ) {
                    Text(
                        text = id.toString(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.background,
                    )
                }
                Text(
                    text = release.uppercase(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }
            Text(
                text = stringResource(R.string.ext_add_dot, title),
                fontSize = 36.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(end = 12.dp, bottom = 18.dp)
            )
            Box(
                modifier = Modifier
                    .padding(bottom = 18.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = stringResource(R.string.desc_album_name, title),
                    placeholder = painterResource(R.drawable.img_placeholder_immersion),
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                )
            }
            Text(
                text = desc,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Row {
                Text(
                    text = song,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = "―",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                )
                Text(
                    text = stringResource(R.string.band_floyd_name),
                    fontSize = 11.sp,
                    color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun AlbumDetailTopBar(
    favCallback: () -> Unit,
    navigateBack: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
                )
                .clickable { navigateBack() }
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.desc_back),
                modifier = Modifier
                    .padding(6.dp)
                    .size(14.dp)
            )
        }
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
                )
                .clickable { favCallback() }
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = stringResource(R.string.fav_page),
                modifier = Modifier
                    .padding(6.dp)
                    .size(14.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumDetailPreview() {
    val data = AlbumsDataSource.albumsData[4]
    PinkFloydComposeTheme(darkTheme = true) {
        Surface {
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 32.dp)
            ) {
                Column(modifier = Modifier.padding(bottom = 27.dp)) { AlbumDetailTopBar({}, {}) }
                AlbumDetailContent(
                    id = data.id,
                    title = data.title,
                    release = data.release,
                    song = data.song,
                    imageUrl = data.imageUrl,
                    desc = data.desc
                )
            }
        }
    }
}