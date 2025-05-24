package io.gw.recordshop.ui.component

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.gw.recordshop.ui.theme.LocalColor

@Composable
fun UiCircularProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.width(32.dp),
        color = LocalColor.current.colorOrange,
        trackColor = LocalColor.current.colorBeige,
    )
}