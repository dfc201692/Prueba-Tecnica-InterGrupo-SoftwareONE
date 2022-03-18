package com.example.prueba.model.models


import com.google.gson.annotations.SerializedName

data class ResponseGetCountriesItem(
    @SerializedName("country_name")
    val countryName: String,
    @SerializedName("country_phone_code")
    val countryPhoneCode: Int,
    @SerializedName("country_short_name")
    val countryShortName: String
)