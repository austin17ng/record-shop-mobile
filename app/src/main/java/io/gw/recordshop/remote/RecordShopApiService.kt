package io.gw.recordshop.remote

import io.gw.recordshop.data.Artist
import retrofit2.http.GET

interface RecordShopApiService {
    @GET("api/artists")
    suspend fun getAllArtists(): List<Artist>
}