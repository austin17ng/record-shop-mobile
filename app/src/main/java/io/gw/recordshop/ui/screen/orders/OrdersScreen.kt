package io.gw.recordshop.ui.screen.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.gw.recordshop.data.Order
import io.gw.recordshop.ui.component.UiAppBar
import io.gw.recordshop.ui.theme.LocalColor
import io.gw.recordshop.ui.theme.LocalTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    viewModel: OrdersViewModel = koinViewModel<OrdersViewModel>(),
) {
    val orders by viewModel.orders.collectAsState()
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
        if (orders.isEmpty()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "You have no orders",
                    style = LocalTypography.current.headlineSmall,
                )
            }
        } else {
            orders.forEach {
                OrderRow(order = it)
            }
        }

    }
}

@Composable
fun OrderRow(
    order: Order
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = "Order ${order.id} - ${order.orderDate}",
                style = LocalTypography.current.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${order.status ?: "PROCESSING"}",
                style = LocalTypography.current.bodyMedium
            )
        }
        Spacer(Modifier.width(16.dp))
    }
}