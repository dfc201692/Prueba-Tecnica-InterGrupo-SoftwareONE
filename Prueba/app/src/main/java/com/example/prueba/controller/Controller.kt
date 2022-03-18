package com.example.prueba.controller

import com.example.prueba.model.api.CountriesApi
import com.example.prueba.model.api.RestApiManager
import com.example.prueba.model.models.ResponseAccessToken
import com.example.prueba.model.models.ResponseGetCountries
import com.example.prueba.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Controller(private val countriesCallbackListener: CountriesCallbackListener) {


    private val apiManager: CountriesApi = RestApiManager().getCountriesApi()

    fun getAccessToken() {
        val call = apiManager.getAccessToken(Constants.API_TOKEN, Constants.USER_EMAIL)

        call.enqueue(object : Callback<ResponseAccessToken> {
            override fun onResponse(
                call: Call<ResponseAccessToken>,
                response: Response<ResponseAccessToken>
            ) {
                if (response.isSuccessful) {
                    countriesCallbackListener.onGetAccessTokenSuccess(
                        response.body()?.authToken ?: return
                    )
                } else
                    countriesCallbackListener.onErrorGetAccessToken()
            }

            override fun onFailure(call: Call<ResponseAccessToken>, t: Throwable) {
                countriesCallbackListener.onErrorGetAccessToken()
            }

        })
    }

    fun getCountries(accessToken: String) {
        val call = apiManager.getCountries("Bearer $accessToken")

        call.enqueue(object : Callback<ResponseGetCountries> {
            override fun onResponse(
                call: Call<ResponseGetCountries>,
                response: Response<ResponseGetCountries>
            ) {
                if (response.isSuccessful)
                    countriesCallbackListener.onGetCountriesSuccess(response.body() ?: return)
                else
                    countriesCallbackListener.onErrorGetCountries()
            }

            override fun onFailure(call: Call<ResponseGetCountries>, t: Throwable) {
                countriesCallbackListener.onErrorGetCountries()
            }

        })
    }

    interface CountriesCallbackListener {
        fun onErrorGetAccessToken()
        fun onGetAccessTokenSuccess(accessToken: String)
        fun onGetCountriesSuccess(responseGetCountries: ResponseGetCountries)
        fun onErrorGetCountries()
    }

}