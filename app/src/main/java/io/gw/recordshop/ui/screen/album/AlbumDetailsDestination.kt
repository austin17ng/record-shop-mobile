package io.gw.recordshop.ui.screen.album

import io.gw.recordshop.data.Album
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDetailsDestination(
    val album: Album
)