package io.gw.recordshop.data

data class Album(
    val id: Long? = null,
    val title: String? = null,
    val artist: Artist? = null,
    val coverUrl: String? = null,
    val releaseDate: String? = null,
    val genre: List<String> = emptyList(),
    val tracks: List<Track> = emptyList(),
    val stockQuantity: Int? = null,
    val price: Double? = null,
)
