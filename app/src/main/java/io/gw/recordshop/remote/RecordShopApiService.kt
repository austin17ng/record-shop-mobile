package io.gw.recordshop.remote

import io.gw.recordshop.data.Album
import io.gw.recordshop.data.Artist
import retrofit2.http.GET

interface RecordShopApiService {
    @GET("api/artists")
    suspend fun getAllArtists(): List<Artist>

    @GET("api/albums/new-arrivals")
    suspend fun getNewArrivalAlbums(): List<Album>
}