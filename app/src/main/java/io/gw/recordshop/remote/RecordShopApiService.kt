package io.gw.recordshop.remote

import io.gw.recordshop.data.AddToCartRequest
import io.gw.recordshop.data.Album
import io.gw.recordshop.data.Artist
import io.gw.recordshop.data.CartItem
import io.gw.recordshop.data.LoginRequest
import io.gw.recordshop.data.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
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

    @POST("api/carts/add-single-item")
    suspend fun addToCart(@Body request: AddToCartRequest): Response<CartItem>

    @GET("api/carts")
    suspend fun getCart(): Response<List<CartItem>>

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}