package com.ianarbuckle.restaurants.network.manager

import com.ianarbuckle.restaurants.network.RestaurantsRetrofitFactory
import com.ianarbuckle.restaurants.network.RestaurantsService
import okhttp3.OkHttpClient

/**
 * Created by Ian Arbuckle on 29/07/2018.
 *
 */
open class DefaultRestaurantsServiceManager(private val okHttpClient: OkHttpClient, private val baseUrl: String) : RestaurantsServiceManager {

    private lateinit var retrofitFactory: RestaurantsRetrofitFactory

    override fun getService(): RestaurantsService {
        retrofitFactory = RestaurantsRetrofitFactory(baseUrl, okHttpClient)
        return retrofitFactory.createService()
    }
}