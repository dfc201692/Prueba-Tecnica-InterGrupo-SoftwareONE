package com.example.prueba.model.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RestApiManager {

    private val BASE_URL = "https://www.universal-tutorial.com/"

    fun getCountriesApi(): CountriesApi {
        val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(CountriesApi::class.java)
    }

}