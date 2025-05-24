package io.gw.recordshop.remote

import io.gw.recordshop.data.LoginRequest
import io.gw.recordshop.data.LoginResponse
import io.gw.recordshop.data.Product
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AppApiService {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("api/v1/products")
    suspend fun getProducts(): List<Product>
}