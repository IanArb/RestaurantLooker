package com.ianarbuckle.restaurants.network.builder

import com.ianarbuckle.restaurants.home.builder.HomeScope
import com.ianarbuckle.restaurants.network.repository.DefaultRestaurantsRepository
import com.ianarbuckle.restaurants.network.repository.RestaurantsRepository
import com.ianarbuckle.restaurants.network.RestaurantsService
import com.ianarbuckle.restaurants.network.manager.DefaultRestaurantsServiceManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
@Module
class NetworkModule constructor(private val okHttpClient: OkHttpClient, private val baseUrl: String) {

    @HomeScope
    @Provides
    fun provideOkHttpClient() = okHttpClient

    @HomeScope
    @Provides
    fun provideRestaurantsService(retrofit: Retrofit): RestaurantsService = retrofit.create(RestaurantsService::class.java)

    @HomeScope
    @Provides
    fun provideRestaurantsRepository(serviceDefault: DefaultRestaurantsServiceManager): RestaurantsRepository = DefaultRestaurantsRepository(serviceDefault)

    @HomeScope
    @Provides
    fun provideRestaurantsServiceManager(): DefaultRestaurantsServiceManager = DefaultRestaurantsServiceManager(okHttpClient, baseUrl)
}