package com.ianarbuckle.restaurants.network

import com.ianarbuckle.retrofitclient.DefaultRetrofitFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by Ian Arbuckle on 29/07/2018.
 *
 */
class RestaurantsRetrofitFactory(baseUrl: String, okHttpClient: OkHttpClient) : DefaultRetrofitFactory(baseUrl, okHttpClient) {

    override fun modifyRetrofit(builder: Retrofit.Builder): Retrofit.Builder {
        return super.modifyRetrofit(builder.addCallAdapterFactory(CoroutineCallAdapterFactory()))
    }

    fun createService(): RestaurantsService = getRetrofit().create(RestaurantsService::class.java)
}