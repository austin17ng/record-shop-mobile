package io.gw.androidquicktemplate.remote

import io.gw.androidquicktemplate.data.LoginRequest
import io.gw.androidquicktemplate.data.LoginResponse
import io.gw.androidquicktemplate.data.Product
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AppApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("api/v1/products")
    suspend fun getProducts(): List<Product>
}