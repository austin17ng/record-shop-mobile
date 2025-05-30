package io.gw.recordshop.ui.screen.album

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.gw.recordshop.MoneyUtils
import io.gw.recordshop.R
import io.gw.recordshop.data.Album
import io.gw.recordshop.data.Artist
import io.gw.recordshop.data.Track
import io.gw.recordshop.ui.common.LoadingHandler
import io.gw.recordshop.ui.component.UiButton
import io.gw.recordshop.ui.theme.LocalColor
import io.gw.recordshop.ui.theme.LocalTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun AlbumDetailsScreen(
    albumId: Long,
    viewModel: AlbumDetailsViewModel = koinViewModel<AlbumDetailsViewModel>(),
) {
    val state by viewModel.state.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val context = LocalContext.current

    LoadingHandler(isLoading)

    LaunchedEffect(albumId) {
        viewModel.getAlbum(albumId)
    }

    LaunchedEffect(Unit) {
        viewModel.addedCartItem.collect {
            Toast.makeText(context, "Album added to cart", Toast.LENGTH_LONG).show()
        }
    }

    AlbumDetailsScreen(
        state = state,
        onEvent = {},
        onAddToCartClicked = {
            viewModel.addToCart()
        }
    )

}

@Composable
fun AlbumDetailsScreen(
    state: AlbumDetailsState,
    onEvent: (AlbumDetailsEvent) -> Unit,
    onAddToCartClicked: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = LocalColor.current.colorSoftScream)
    ) {
        Spacer(Modifier.height(32.dp))
        AsyncImage(
            model = state.album.coverUrl,
            contentDescription = null,
            error = painterResource(id = R.drawable.never_mind_cover),
            placeholder = painterResource(id = R.drawable.never_mind_cover),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(horizontal = 96.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = state.album.title ?: "",
            style = LocalTypography.current.displaySmall,
            color = LocalColor.current.colorBlack,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = state.album.artist?.name ?: "",
            style = LocalTypography.current.labelLarge,
            color = LocalColor.current.colorBlack,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Details",
            style = LocalTypography.current.headlineSmall,
            color = LocalColor.current.colorBlack,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
        Row {
            Text(
                text = "Label:",
                style = LocalTypography.current.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                color = LocalColor.current.colorBlack,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(Modifier.width(2.dp))
            Text(
                text = state.album.label ?: "",
                style = LocalTypography.current.bodyMedium,
                color = LocalColor.current.colorBlack,
            )
        }
        Spacer(Modifier.height(2.dp))
        Row {
            Text(
                text = "Genre:",
                style = LocalTypography.current.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                color = LocalColor.current.colorBlack,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(Modifier.width(2.dp))
            Text(
                text = state.album.genre ?: "",
                style = LocalTypography.current.bodyMedium,
                color = LocalColor.current.colorBlack,
            )
        }
        Spacer(Modifier.height(2.dp))
        Row {
            Text(
                text = "Release Date:",
                style = LocalTypography.current.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                color = LocalColor.current.colorBlack,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(Modifier.width(2.dp))
            Text(
                text = state.album.releaseDate ?: "",
                style = LocalTypography.current.bodyMedium,
                color = LocalColor.current.colorBlack,
            )
        }
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Tracklist",
            style = LocalTypography.current.headlineSmall,
            color = LocalColor.current.colorBlack,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
        state.album.tracks.forEachIndexed { index, track ->
            Text(
                text = "${index + 1}. ${track.title}",
                style = LocalTypography.current.bodyMedium,
                color = LocalColor.current.colorBlack,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        Spacer(Modifier.height(24.dp))
        UiButton(
            modifier = Modifier.padding(start = 16.dp),
            text = "Add to cart",
            onClick = {
                onAddToCartClicked()
            }
        )
    }
}

@Preview
@Composable
fun AlbumDetailsScreenPreview() {
    val state = AlbumDetailsState(
        album = Album(
            id = 1L,
            title = "The Dark Side of the Moon",
            description = "An iconic album by Pink Floyd, known for its progressive rock sound and conceptual themes.",
            artist = Artist(name = "Pink Floyd"),
            coverUrl = "https://example.com/covers/dark_side_of_the_moon.jpg",
            releaseDate = "1973-03-01",
            genre = "Rock",
            tracks = listOf(
                Track(trackNumber = 1, title = "Speak to Me", duration = 123),
                Track(trackNumber = 2, title = "Breathe", duration = 1231),
                Track(trackNumber = 3, title = "Time", duration = 1321),
                Track(trackNumber = 4, title = "Money", duration = 1321)
            ),
            stockQuantity = 12,
            price = 11.99,
            label = "Harvest"
        )
    )
    AlbumDetailsScreen(
        state = state,
        onAddToCartClicked = {},
        onEvent = {}
    )
}