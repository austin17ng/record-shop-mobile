package io.gw.recordshop.ui.screen.favourite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.gw.recordshop.MoneyUtils
import io.gw.recordshop.R
import io.gw.recordshop.data.Album
import io.gw.recordshop.data.Artist
import io.gw.recordshop.data.Track
import io.gw.recordshop.ui.component.UiAppBar
import io.gw.recordshop.ui.noRippleClickable
import io.gw.recordshop.ui.theme.LocalColor
import io.gw.recordshop.ui.theme.LocalTypography

@Composable
fun FavouriteScreen(
    state: FavouriteState,
    onEvent: (FavouriteEvent) -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = LocalColor.current.colorSoftScream)
            .verticalScroll(rememberScrollState())
    ) {
        UiAppBar(
            title = "Favourite"
        )
        Spacer(Modifier.height(24.dp))
        state.albums.forEach { cartItem ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                AsyncImage(
                    model = cartItem.coverUrl,
                    contentDescription = null,
                    error = painterResource(id = R.drawable.never_mind_cover),
                    placeholder = painterResource(id = R.drawable.never_mind_cover),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(Modifier.width(16.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        text = cartItem.title ?: "",
                        maxLines = 2,
                        style = LocalTypography.current.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = MoneyUtils.formatMoney(cartItem.price ?: 0.0),
                        style = LocalTypography.current.bodyMedium
                    )
                }
                Spacer(Modifier.weight(1f))
                Icon(
                    painterResource(R.drawable.ic_delete),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically).noRippleClickable {

                    }
                )
                Spacer(Modifier.width(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun FavouriteScreenPreview() {
    val albums = listOf(
        Album(
            id = 1L,
            title = "Dark Side of the Moon",
            description = "A timeless classic by Pink Floyd.",
            artist = Artist(id = 1L, name = "Pink Floyd"),
            coverUrl = "https://example.com/covers/dark_side.jpg",
            releaseDate = "1973-03-01",
            genre = "Progressive Rock",
            tracks = listOf(
                Track(1L, "Speak to Me", 90),
                Track(2L, "Breathe", 163),
                Track(3L, "Time", 412)
            ),
            stockQuantity = 10,
            price = 19.99,
            label = "Harvest Records"
        ),
        Album(
            id = 2L,
            title = "Thriller",
            description = "The best-selling album of all time.",
            artist = Artist(id = 2L, name = "Michael Jackson"),
            coverUrl = "https://example.com/covers/thriller.jpg",
            releaseDate = "1982-11-30",
            genre = "Pop",
            tracks = listOf(
                Track(1L, "Wanna Be Startin' Somethin'", 362),
                Track(2L, "Thriller", 358)
            ),
            stockQuantity = 5,
            price = 17.49,
            label = "Epic"
        ),
        Album(
            id = 3L,
            title = "Back in Black",
            description = "Hard rock perfection by AC/DC.",
            artist = Artist(id = 3L, name = "AC/DC"),
            coverUrl = "https://example.com/covers/back_in_black.jpg",
            releaseDate = "1980-07-25",
            genre = "Hard Rock",
            tracks = listOf(
                Track(1L, "Hells Bells", 312),
                Track(2L, "Back in Black", 255)
            ),
            stockQuantity = 8,
            price = 15.99,
            label = "Atlantic"
        )
    )
    val state = FavouriteState(
        albums = albums
    )
    FavouriteScreen(state = state) { }
}