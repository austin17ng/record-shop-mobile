package io.gw.recordshop.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.gw.recordshop.ui.component.UiAppBar
import io.gw.recordshop.ui.noRippleClickable
import io.gw.recordshop.ui.theme.LocalColor

@Composable
fun ProfileScreen(
    onOrdersClicked: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = LocalColor.current.colorSoftScream)
            .verticalScroll(rememberScrollState())
    ) {
        UiAppBar(
            title = "Profile"
        )
        Spacer(Modifier.height(24.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    color = LocalColor.current.colorBeige,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Text(
                modifier = Modifier
                    .noRippleClickable {
                        onOrdersClicked()
                    }
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                text = "Your orders"
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                text = "Settings"
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                text = "Log out"
            )

        }
    }

}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        onOrdersClicked = {}
    )
}