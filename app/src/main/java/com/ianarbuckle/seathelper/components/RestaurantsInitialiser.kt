package com.ianarbuckle.seathelper.components

import com.ianarbuckle.restaurants.RestaurantsInitializer
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
class RestaurantsInitialiser(private val baseUrl: String, private val okHttpClient: OkHttpClient) {

    fun init() = RestaurantsInitializer.Builder().apply {
        withBaseUrl(baseUrl)
        withOkHttpClient(okHttpClient)
        withConverterFactory(MoshiConverterFactory.create())
    }.build()
}