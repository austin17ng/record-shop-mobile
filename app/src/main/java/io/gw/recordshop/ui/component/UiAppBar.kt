package io.gw.recordshop.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.gw.recordshop.ui.theme.LocalColor
import io.gw.recordshop.ui.theme.LocalTypography

@Composable
fun UiAppBar(
    title: String,
    @DrawableRes menuIcon: Int? = null,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(color = LocalColor.current.colorOrange)
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                title,
                style = LocalTypography.current.displayLarge,
                color = LocalColor.current.colorOffWhite
            )
            Spacer(Modifier.weight(1f))
            menuIcon?.let {
                Icon(
                    painter = painterResource(it),
                    tint = LocalColor.current.colorOffWhite,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }

        }
    }
}