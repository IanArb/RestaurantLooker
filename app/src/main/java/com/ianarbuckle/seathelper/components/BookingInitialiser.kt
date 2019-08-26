package com.ianarbuckle.seathelper.components

import com.ianarbuckle.booking.BookingInitializer
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Ian Arbuckle on 2019-08-25.
 *
 */
class BookingInitialiser(private val baseUrl: String, private val okHttpClient: OkHttpClient) {

    fun init() = BookingInitializer.Builder()
            .withBaseUrl(baseUrl)
            .withOkHttpClient(okHttpClient)
            .withConverterFactory(MoshiConverterFactory.create())
            .build()
}