package com.ianarbuckle.booking.network.manager

import com.ianarbuckle.booking.network.BookingService
import com.ianarbuckle.booking.network.factory.BookingRetrofitFactory
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */

interface BookingServiceManager {
    fun getBookingService(): BookingService
}

class BookingServiceManagerImpl(private val okHttpClient: OkHttpClient, private val baseUrl: String, private val converterFactory: Converter.Factory): BookingServiceManager {

    private lateinit var retrofitFactory: BookingRetrofitFactory

    override fun getBookingService(): BookingService {
        retrofitFactory = BookingRetrofitFactory(baseUrl, okHttpClient, converterFactory)
        return retrofitFactory.createService()
    }
}
