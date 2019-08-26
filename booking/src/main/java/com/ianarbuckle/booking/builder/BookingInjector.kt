package com.ianarbuckle.booking.builder

import com.ianarbuckle.booking.network.builder.NetworkModule
import com.ianarbuckle.booking.ui.bookings.BookingFragment
import com.ianarbuckle.booking.ui.bookings.builder.BookingModule
import com.ianarbuckle.booking.ui.bookings.builder.DaggerBookingComponent
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingInjector {
    fun inject(fragment: BookingFragment)
}

class BookingInjectorImpl(private val baseUrl: String, private val okHttpClient: OkHttpClient,
                          private val converterFactory: Converter.Factory): BookingInjector {

    override fun inject(fragment: BookingFragment) {
        DaggerBookingComponent.builder()
                .bookingModule(BookingModule(fragment))
                .networkModule(NetworkModule(okHttpClient, baseUrl, converterFactory))
                .build()
                .inject(fragment)
    }
}
