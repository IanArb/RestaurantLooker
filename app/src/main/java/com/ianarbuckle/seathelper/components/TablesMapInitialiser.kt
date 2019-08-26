package com.ianarbuckle.seathelper.components

import com.ianarbuckle.tablemap.TablesMapInitializer
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Ian Arbuckle on 2019-08-01.
 *
 */
class TablesMapInitializer(private val baseUrl: String, private val okHttpClient: OkHttpClient) {

    fun init() = TablesMapInitializer.Builder()
            .withBaseUrl(baseUrl)
            .withOkHttpClient(okHttpClient)
            .withConverterFactory(MoshiConverterFactory.create())
            .build()
}