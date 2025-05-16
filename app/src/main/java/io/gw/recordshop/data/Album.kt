package io.gw.recordshop.data

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: Long? = null,
    val title: String? = null,
    val description: String? = null,
    val artist: Artist? = null,
    val coverUrl: String? = null,
    val releaseDate: String? = null,
    val genre: String? = null,
    val tracks: List<Track> = emptyList(),
    val stockQuantity: Int? = null,
    val price: Double? = null,
)
