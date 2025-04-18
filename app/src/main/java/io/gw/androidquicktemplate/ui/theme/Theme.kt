package io.gw.androidquicktemplate.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalTypography provides AppTypography(),
    ) {
        content()
    }

}