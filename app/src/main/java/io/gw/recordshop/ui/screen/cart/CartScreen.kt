package io.gw.recordshop.ui.screen.cart

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
import io.gw.recordshop.data.CartItem
import io.gw.recordshop.ui.component.UiAppBar
import io.gw.recordshop.ui.component.UiBottomNavigation
import io.gw.recordshop.ui.component.UiBottomNavigationItem
import io.gw.recordshop.ui.component.UiButton
import io.gw.recordshop.ui.theme.LocalColor
import io.gw.recordshop.ui.theme.LocalTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartScreen(
    viewModel: CartViewModel = koinViewModel<CartViewModel>(),
) {
    val state by viewModel.state.collectAsState()
    CartScreen(state = state) {

    }
}

@Composable
fun CartScreen(
    state: CartState,
    onEvent: (CartEvent) -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = LocalColor.current.colorSoftScream)
            .verticalScroll(rememberScrollState())
    ) {
        UiAppBar(
            title = "Cart"
        )
        Spacer(Modifier.height(24.dp))
        state.cartItems.forEach { cartItem ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                AsyncImage(
                    model = cartItem.album.coverUrl,
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
                        text = cartItem.album.title ?: "",
                        maxLines = 2,
                        style = LocalTypography.current.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = MoneyUtils.formatMoney(cartItem.album.price ?: 0.0),
                        style = LocalTypography.current.bodyMedium
                    )
                }
                Spacer(Modifier.width(16.dp))
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .background(
                            color = LocalColor.current.colorLightGray,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "-",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        textAlign = TextAlign.Center,
                        style = LocalTypography.current.bodyMedium,
                    )
                    Text(
                        text = "${cartItem.quantity}",
                        style = LocalTypography.current.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    )
                    Text(
                        text = "+",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        textAlign = TextAlign.Center,
                        style = LocalTypography.current.bodyMedium,
                    )
                }

            }
        }
        Spacer(Modifier.height(24.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = LocalColor.current.colorLightGray
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Total: " + MoneyUtils.formatMoney(state.getTotalPrice()),
            style = LocalTypography.current.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
        UiButton(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = "Check out",
            onClick = {})
    }
}

@Preview
@Composable
fun CartScreenPreview() {
    val state = CartState(
        cartItems = listOf(
            CartItem(
                album = Album(
                    id = 1L,
                    title = "Abbey Road",
                    description = "A classic album by The Beatles, featuring iconic tracks like 'Come Together' and 'Here Comes the Sun'.",
                    artist = Artist(name = "The Beatles"),
                    genre = "Rock",
                    stockQuantity = 5,
                    price = 12.99
                ),
                quantity = 1,
            ),
            CartItem(
                album = Album(
                    id = 2L,
                    title = "Random Access Memories",
                    description = "An electronic masterpiece by Daft Punk with a blend of disco and funk.",
                    artist = Artist(name = "Daft Punk"),
                    coverUrl = "https://example.com/covers/random_access_memories.jpg",
                    releaseDate = "2013-05-17",
                    genre = "Funk",
                    stockQuantity = 10,
                    price = 14.49
                ),
                quantity = 2,
            ),
            CartItem(
                album = Album(
                    id = 3L,
                    title = "21",
                    description = "A soulful album from Adele that features emotional ballads and chart-topping hits.",
                    artist = Artist(name = "Adele"),
                    coverUrl = "https://example.com/covers/21.jpg",
                    releaseDate = "2011-01-24",
                    genre = "Pop",
                    price = 10.99
                ),
                quantity = 1,
            )
        )
    )
    Scaffold(bottomBar = {
        UiBottomNavigation(selectedItem = UiBottomNavigationItem.CART)
    }) { padding ->
        CartScreen(state = state) { }
    }
}