package com.ianarbuckle.booking.network.factory

import com.ianarbuckle.booking.network.BookingService
import com.ianarbuckle.core.retrofit.DefaultRetrofitFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */

class BookingRetrofitFactory(baseUrl: String, okHttpClient: OkHttpClient, converterFactory: Converter.Factory) : DefaultRetrofitFactory(baseUrl, okHttpClient, converterFactory) {

    override fun modifyRetrofit(builder: Retrofit.Builder): Retrofit.Builder {
        return super.modifyRetrofit(builder.addCallAdapterFactory(CoroutineCallAdapterFactory()))
    }

    fun createService(): BookingService = getRetrofit().create(BookingService::class.java)
}
