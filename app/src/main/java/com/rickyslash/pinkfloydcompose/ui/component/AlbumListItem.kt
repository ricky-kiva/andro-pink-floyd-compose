package com.rickyslash.pinkfloydcompose.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rickyslash.pinkfloydcompose.ui.theme.PinkFloydComposeTheme
import com.rickyslash.pinkfloydcompose.R

@Composable
fun AlbumListItem(imageUrl: String, title: String, release: String, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(0.7f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(1f, false)
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.desc_arrow),
                        modifier = Modifier
                            .padding(top = 2.dp, start = 4.dp)
                            .size(11.dp)
                    )
                }
                Text(
                    text = release,
                    fontSize = 11.sp,
                    color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
                )
            }
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        shape = CircleShape,
                        color = (MaterialTheme.colors.onSecondary).copy(alpha = 0.5f)
                    )
                    .weight(0.3f, false)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = stringResource(R.string.desc_album_name, title),
                    placeholder = painterResource(R.drawable.img_placeholder_dsod_wembley),
                    modifier = Modifier
                        .size(64.dp)
                        .padding(12.dp)
                        .clip(CircleShape)
                )
            }
        }
        Divider(color = MaterialTheme.colors.onSecondary.copy(alpha = 0.25f))
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumListItemPreview() {
    PinkFloydComposeTheme(darkTheme = true) {
        Surface {
            AlbumListItem(
                "https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png",
                "Dark Side of the Moon",
                "March 1, 1973"
            )
        }
    }
}