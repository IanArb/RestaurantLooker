package com.ianarbuckle.restaurants.network.manager

import com.ianarbuckle.restaurants.network.RestaurantsRetrofitFactory
import com.ianarbuckle.restaurants.network.RestaurantsService
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 29/07/2018.
 *
 */
open class DefaultRestaurantsServiceManager(private val okHttpClient: OkHttpClient, private val baseUrl: String, private val converterFactory: Converter.Factory) : RestaurantsServiceManager {

    private lateinit var retrofitFactory: RestaurantsRetrofitFactory

    override fun getService(): RestaurantsService {
        retrofitFactory = RestaurantsRetrofitFactory(baseUrl, okHttpClient, converterFactory)
        return retrofitFactory.createService()
    }
}