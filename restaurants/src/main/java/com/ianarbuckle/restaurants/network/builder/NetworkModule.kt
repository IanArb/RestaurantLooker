package com.ianarbuckle.restaurants.network.builder

import com.ianarbuckle.database.client.DatabaseClient
import com.ianarbuckle.restaurants.ui.home.builder.HomeScope
import com.ianarbuckle.restaurants.ui.home.core.repository.DefaultRestaurantsRepository
import com.ianarbuckle.restaurants.ui.home.core.repository.RestaurantsRepository
import com.ianarbuckle.restaurants.network.RestaurantsService
import com.ianarbuckle.restaurants.network.manager.DefaultRestaurantsServiceManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
@Module
class NetworkModule(private val okHttpClient: OkHttpClient, private val baseUrl: String, private val converterFactory: Converter.Factory,
                    private val databaseClient: DatabaseClient) {

    @HomeScope
    @Provides
    fun provideOkHttpClient() = okHttpClient

    @HomeScope
    @Provides
    fun provideRestaurantsService(retrofit: Retrofit): RestaurantsService = retrofit.create(RestaurantsService::class.java)

    @HomeScope
    @Provides
    fun provideRestaurantsRepository(serviceManager: DefaultRestaurantsServiceManager): RestaurantsRepository
            = DefaultRestaurantsRepository(serviceManager, databaseClient.getRestaurantsDAO())

    @HomeScope
    @Provides
    fun provideRestaurantsServiceManager(): DefaultRestaurantsServiceManager = DefaultRestaurantsServiceManager(okHttpClient, baseUrl, converterFactory)
}