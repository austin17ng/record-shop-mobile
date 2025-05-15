package io.gw.recordshop.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalTypography provides AppTypography(),
        LocalColor provides AppColor()
    ) {
        content()
    }

}