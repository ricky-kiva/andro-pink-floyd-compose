package com.rickyslash.pinkfloydcompose.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rickyslash.pinkfloydcompose.ui.theme.PinkFloydComposeTheme
import com.rickyslash.pinkfloydcompose.R

@Composable
fun AlbumListItem(imageUrl: String, title: String, release: String, onItemClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(end = 16.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = release
            )
        }
        AsyncImage(
            model = imageUrl,
            contentDescription = stringResource(R.string.desc_album_name, title),
            placeholder = painterResource(R.drawable.img_placeholder_dsod),
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Divider()
    }

}

@Preview(showBackground = true)
@Composable
fun AlbumListItemPreview() {
    PinkFloydComposeTheme {
        AlbumListItem(
            "https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png",
            "Dark Side of the Moon",
            "March 1, 1973"
        ) {}
    }
}