package io.gw.recordshop.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.gw.recordshop.ui.theme.LocalColor
import io.gw.recordshop.ui.theme.LocalTypography

@Composable
fun UiButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Text(
        modifier = modifier
            .clickable {
                onClick()
            }
            .background(
                color = LocalColor.current.colorOrange,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 8.dp, horizontal = 16.dp), text = text,
        style = LocalTypography.current.labelSmall,
        color = LocalColor.current.colorOffWhite
    )
}

@Preview
@Composable
fun UiButtonPreview() {
    UiButton(text = "Add to cart") {

    }
}