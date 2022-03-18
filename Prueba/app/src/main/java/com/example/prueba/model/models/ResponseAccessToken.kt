package com.example.prueba.model.models


import com.google.gson.annotations.SerializedName

data class ResponseAccessToken(
    @SerializedName("auth_token")
    val authToken: String
)