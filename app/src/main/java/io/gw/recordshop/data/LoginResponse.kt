package io.gw.recordshop.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") val token: String,
    @SerializedName("userId") val userId: Int
)
