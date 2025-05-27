package io.gw.recordshop.data

data class Order(
    val id: Long,
    val orderDate: String,
    val status: String,
    val orderItems: List<OrderItem>
)

data class OrderItem(
    val id: Long,
    val album: Album
)