package com.ianarbuckle.restaurants

import com.ianarbuckle.restaurants.builder.DefaultRestaurantsInjector
import com.ianarbuckle.restaurants.builder.RestaurantsInjector
import okhttp3.OkHttpClient

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
data class RestaurantsInitializer(val baseUrl: String, val okHttpClient: OkHttpClient) {

    fun init() {
        val injector: RestaurantsInjector = DefaultRestaurantsInjector(okHttpClient, baseUrl)
        RestaurantsProvider.init(injector)
    }

    class Builder {
        private lateinit var baseUrl: String
        private lateinit var okHttpClient: OkHttpClient

        fun withBaseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun withOkHttpClient(okHttpClient: OkHttpClient) = apply { this.okHttpClient = okHttpClient }

        fun build() {
            val initializer = RestaurantsInitializer(baseUrl, okHttpClient)
            initializer.init()
        }
    }

}