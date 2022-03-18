package com.example.prueba.model.api

import com.example.prueba.model.models.ResponseAccessToken
import com.example.prueba.model.models.ResponseGetCountries
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface CountriesApi {

    @GET("/api/getaccesstoken")
    fun getAccessToken(
        @Header("api-token") apiToken: String,
        @Header("user-email") userEmail: String
    ): Call<ResponseAccessToken>

    @GET("/api/countries")
    fun getCountries(
        @Header("Authorization") authorization: String
    ): Call<ResponseGetCountries>

}