package io.gw.recordshop.data

import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val id: Long? = null,
    val title: String? = null,
    val duration: Int? = null,
    val trackNumber: Int? = null,
)
