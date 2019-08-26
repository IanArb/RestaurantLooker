package com.ianarbuckle.booking.network.builder

import com.ianarbuckle.booking.network.manager.BookingServiceManager
import com.ianarbuckle.booking.network.manager.BookingServiceManagerImpl
import com.ianarbuckle.booking.ui.bookings.builder.BookingScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
@Module
class NetworkModule(private val okHttpClient: OkHttpClient, private val baseUrl: String, private val converterFactory: Converter.Factory) {

    @BookingScope
    @Provides
    fun provideBookingServiceManager(): BookingServiceManager = BookingServiceManagerImpl(okHttpClient, baseUrl, converterFactory)
}