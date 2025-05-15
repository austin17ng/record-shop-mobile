package io.gw.recordshop.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val LocalTypography = staticCompositionLocalOf { AppTypography() }
val LocalColor = staticCompositionLocalOf { AppColor() }

@Immutable
class AppColor(
    val colorSoftScream: Color = Color(0xFFFDF7ED),
    val colorBlack: Color = Color(0xFF121212),
    val colorOrange: Color = Color(0xFFF28C3A),
    val colorLightGray: Color = Color(0xFFE7E3DC),
    val colorOffWhite: Color = Color(0xFFFCFAF7),
    val colorBeige: Color = Color(0xFFEFE6D9)
)

@Immutable
class AppTypography(
    val bodyLarge: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)