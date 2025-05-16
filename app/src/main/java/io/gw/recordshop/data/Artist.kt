package io.gw.recordshop.data

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val id: Long? = null,
    val name: String? = null,
    val country: String? = null,
    val genre: String? = null
)
