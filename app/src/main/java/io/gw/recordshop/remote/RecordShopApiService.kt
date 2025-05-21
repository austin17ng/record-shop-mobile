package io.gw.recordshop.remote

import io.gw.recordshop.data.Album
import io.gw.recordshop.data.Artist
import retrofit2.http.GET
import retrofit2.http.Path

interface RecordShopApiService {
    @GET("api/artists")
    suspend fun getAllArtists(): List<Artist>

    @GET("api/albums/new-arrivals")
    suspend fun getNewArrivalAlbums(): List<Album>

    @GET("api/albums/trending")
    suspend fun getTrendingAlbums(): List<Album>

    @GET("api/albums/staff-picks")
    suspend fun getStaffPicksAlbums(): List<Album>

    @GET("api/albums/sale")
    suspend fun getSaleAlbums(): List<Album>

    @GET("api/albums/{albumId}")
    suspend fun getAlbum(@Path("albumId") albumId: Long): Album
}