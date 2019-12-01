package com.ianarbuckle.seathelper.components

import com.ianarbuckle.database.client.DatabaseClient
import com.ianarbuckle.restaurants.RestaurantsInitializer
import com.ianarbuckle.restaurants.RestaurantsNavigator
import com.ianarbuckle.restaurants.restaurantsInitializer
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
class RestaurantsInitialiser(private val baseUrl: String, private val okHttpClient: OkHttpClient, private val navigator: RestaurantsNavigator,
                             private val databaseClient: DatabaseClient) {

    fun init() = restaurantsInitializer {
        withBaseUrl(baseUrl)
        withOkHttpClient(okHttpClient)
        withConverterFactory(MoshiConverterFactory.create())
        withNavigator(navigator)
        withDatabaseClient(databaseClient)
    }
}