package io.gw.recordshop.ui.screen.home

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.gw.recordshop.MoneyUtils
import io.gw.recordshop.R
import io.gw.recordshop.data.Album
import io.gw.recordshop.data.Artist
import io.gw.recordshop.ui.common.LoadingHandler
import io.gw.recordshop.ui.component.UiAppBar
import io.gw.recordshop.ui.component.UiBottomNavigation
import io.gw.recordshop.ui.component.UiBottomNavigationItem
import io.gw.recordshop.ui.noRippleClickable
import io.gw.recordshop.ui.theme.LocalColor
import io.gw.recordshop.ui.theme.LocalTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    onAlbumClicked: (Album) -> Unit,
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.networkError.collect {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    LoadingHandler(isLoading)

    HomeScreen(state) { event ->
        when (event) {
            is HomeEvent.OnAlbumClicked -> {
                onAlbumClicked(event.album)
            }
        }
    }
}

@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(color = LocalColor.current.colorSoftScream)
    ) {
        item(key = "app bar") {
            UiAppBar(
                title = "Record shop"
            )
            Spacer(Modifier.height(24.dp))
        }
        items(state.homeSections) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 16.dp),
                    text = it.title,
                    style = LocalTypography.current.headlineMedium,
                    color = LocalColor.current.colorBlack,
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(it.albums) { album ->
                        AlbumCard(
                            album = album,
                            onClicked = {
                                onEvent(HomeEvent.OnAlbumClicked(album))
                            }
                        )
                    }
                }
            }
        }
        item(key = "bottom_spacer") {
            Spacer(Modifier.height(64.dp))
        }
    }
}

@Composable
fun AlbumCard(
    album: Album,
    onClicked: (Album) -> Unit
) {
    Column(Modifier
        .width(120.dp)
        .noRippleClickable {
            onClicked(album)
        }
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                model = album.coverUrl,
                contentDescription = null,
                error = painterResource(id = R.drawable.never_mind_cover),
                placeholder = painterResource(id = R.drawable.never_mind_cover),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = album.title ?: "",
            style = LocalTypography.current.headlineSmall,
            color = LocalColor.current.colorBlack,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = album.artist?.name ?: "",
            style = LocalTypography.current.bodyMedium,
            color = LocalColor.current.colorBlack,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = MoneyUtils.formatMoney(album.price ?: 0.0),
            style = LocalTypography.current.bodyMedium.copy(fontWeight = FontWeight.ExtraBold),
            color = LocalColor.current.colorOrange,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val state = HomeState(
        homeSections = listOf(
            HomeSection(
                title = "New arrivals",
                albums = listOf(
                    Album(
                        id = 0L,
                        title = "The Tortured Poets Department",
                        artist = Artist(name = "Taylor Swift"),
                        price = 14.99
                    ),
                    Album(
                        id = 1L,
                        title = "Endless Summer Vacation",
                        artist = Artist(name = "Miley Cyrus"),
                        price = 13.49
                    ),
                    Album(
                        id = 2L,
                        title = "Utopia",
                        artist = Artist(name = "Travis Scott"),
                        price = 15.99
                    ),
                    Album(
                        id = 3L,
                        title = "Hackney Diamonds",
                        artist = Artist(name = "The Rolling Stones"),
                        price = 16.99
                    ),
                )
            ),
            HomeSection(
                title = "Trending",
                albums = listOf(
                    Album(
                        id = 4L,
                        title = "One Thing at a Time",
                        artist = Artist(name = "Morgan Wallen"),
                        price = 12.99
                    ),
                    Album(
                        id = 5L,
                        title = "1989 (Taylor's Version)",
                        artist = Artist(name = "Taylor Swift"),
                        price = 14.49
                    ),
                    Album(
                        id = 6L,
                        title = "Guts",
                        artist = Artist(name = "Olivia Rodrigo"),
                        price = 13.99
                    ),
                    Album(
                        id = 7L,
                        title = "For All the Dogs",
                        artist = Artist(name = "Drake"),
                        price = 15.49
                    ),
                )
            ),
            HomeSection(
                title = "Staff picks",
                albums = listOf(
                    Album(
                        id = 8L,
                        title = "Blue Rev",
                        artist = Artist(name = "Alvvays"),
                        price = 11.99
                    ),
                    Album(
                        id = 9L,
                        title = "Being Funny in a Foreign Language",
                        artist = Artist(name = "The 1975"),
                        price = 13.99
                    ),
                    Album(
                        id = 10L,
                        title = "The Record",
                        artist = Artist(name = "boygenius"),
                        price = 14.99
                    ),
                    Album(
                        id = 11L,
                        title = "SOUR",
                        artist = Artist(name = "Olivia Rodrigo"),
                        price = 12.49
                    ),
                )
            ),
            HomeSection(
                title = "Sale",
                albums = listOf(
                    Album(
                        id = 12L,
                        title = "Rumours",
                        artist = Artist(name = "Fleetwood Mac"),
                        price = 8.99
                    ),
                    Album(
                        id = 13L,
                        title = "Abbey Road",
                        artist = Artist(name = "The Beatles"),
                        price = 9.49
                    ),
                    Album(
                        id = 14L,
                        title = "Nevermind",
                        artist = Artist(name = "Nirvana"),
                        price = 7.99
                    ),
                    Album(
                        id = 15L,
                        title = "Back in Black",
                        artist = Artist(name = "AC/DC"),
                        price = 6.99
                    ),
                )
            ),
        )
    )

    Scaffold(bottomBar = {
        UiBottomNavigation(selectedItem = UiBottomNavigationItem.HOME)
    }) { padding ->
        HomeScreen(state) { }
    }
}