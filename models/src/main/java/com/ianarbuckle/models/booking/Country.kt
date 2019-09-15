package com.ianarbuckle.models.booking

import com.google.gson.annotations.SerializedName

/**
 * Created by Ian Arbuckle on 2019-09-05.
 *
 */
data class Country(
        @SerializedName("countryName")
        val countryName: String,
        @SerializedName("ISO")
        val iso: String,
        @SerializedName("phoneCode")
        val phoneCode: String)