package com.ianarbuckle.restaurants

import com.ianarbuckle.database.DatabaseInitialiser
import com.ianarbuckle.database.client.DatabaseClient
import com.ianarbuckle.restaurants.builder.DefaultRestaurantsInjector
import com.ianarbuckle.restaurants.builder.RestaurantsInjector
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
data class RestaurantsInitializer(val baseUrl: String, val okHttpClient: OkHttpClient, val converterFactory: Converter.Factory, private val navigator: RestaurantsNavigator,
                                  private val databaseClient: DatabaseClient) {

    fun init() {
        val injector: RestaurantsInjector =
                DefaultRestaurantsInjector(
                        okHttpClient,
                        baseUrl,
                        converterFactory,
                        navigator,
                        databaseClient)
        RestaurantsProvider.init(injector)
    }

    class Builder {
        private lateinit var baseUrl: String
        private lateinit var okHttpClient: OkHttpClient
        private lateinit var converterFactory: Converter.Factory
        private lateinit var navigator: RestaurantsNavigator
        private lateinit var databaseClient: DatabaseClient

        fun withBaseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun withOkHttpClient(okHttpClient: OkHttpClient) = apply { this.okHttpClient = okHttpClient }
        fun withConverterFactory(converterFactory: Converter.Factory) = apply { this.converterFactory = converterFactory }
        fun withNavigator(navigator: RestaurantsNavigator) = apply { this.navigator = navigator }
        fun withDatabaseClient(databaseClient: DatabaseClient) = apply { this.databaseClient = databaseClient }

        fun build(): RestaurantsInitializer {
            val initializer =
                    RestaurantsInitializer(
                    baseUrl,
                    okHttpClient,
                    converterFactory,
                    navigator,
                    databaseClient)
            initializer.init()
            return initializer
        }
    }

}

fun restaurantsInitializer(initializer: RestaurantsInitializer.Builder.() -> Unit): RestaurantsInitializer {
    return RestaurantsInitializer.Builder().apply(initializer).build()
}