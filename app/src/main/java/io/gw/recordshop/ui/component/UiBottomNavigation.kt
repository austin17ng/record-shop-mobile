package io.gw.recordshop.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.gw.recordshop.R
import io.gw.recordshop.ui.theme.LocalColor
import io.gw.recordshop.ui.theme.LocalTypography

@Composable
fun UiBottomNavigation(
    selectedItem: UiBottomNavigationItem = UiBottomNavigationItem.HOME,
    onClick: (UiBottomNavigationItem) -> Unit = {}
) {
    Column(Modifier.fillMaxWidth()) {
        HorizontalDivider(color = LocalColor.current.colorLightGray)
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = LocalColor.current.colorSoftScream)
        ) {
            UiBottomNavigationItem.values().forEach {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onClick(it)
                        }
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = if (it == selectedItem) painterResource(id = it.iconSelected) else painterResource(
                            id = it.iconUnselected
                        ),
                        contentDescription = it.label,
                        tint = LocalColor.current.colorBlack
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = it.label,
                        color = LocalColor.current.colorBlack,
                        style = if (it == selectedItem) LocalTypography.current.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        ) else LocalTypography.current.labelLarge
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun UiBottomNavigationPreview() {
    UiBottomNavigation()
}

enum class UiBottomNavigationItem(
    val iconSelected: Int,
    val iconUnselected: Int,
    val label: String
) {
    HOME(R.drawable.ic_home_filled, R.drawable.ic_home, "Home"),
    FAV(R.drawable.ic_fav_filled, R.drawable.ic_fav, "Favourite"),
    CART(R.drawable.ic_cart_filled, R.drawable.ic_cart, "Cart"),
    PROFILE(R.drawable.ic_profile_filled, R.drawable.ic_profile, "Profile")
}