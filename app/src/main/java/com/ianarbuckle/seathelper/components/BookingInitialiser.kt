package com.ianarbuckle.seathelper.components

import com.ianarbuckle.booking.BookingInitializer
import com.ianarbuckle.booking.BookingNavigator
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Ian Arbuckle on 2019-08-25.
 *
 */
class BookingInitialiser(private val baseUrl: String, private val okHttpClient: OkHttpClient, private val navigator: BookingNavigator, private val country: String) {

    fun init() = BookingInitializer.Builder()
            .withBaseUrl(baseUrl)
            .withOkHttpClient(okHttpClient)
            .withConverterFactory(MoshiConverterFactory.create())
            .withNavigator(navigator)
            .withCountry(country)
            .build()
}