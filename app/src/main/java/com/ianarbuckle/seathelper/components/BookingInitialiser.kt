package com.ianarbuckle.seathelper.components

import android.content.Intent
import com.ianarbuckle.booking.BookingInitializer
import com.ianarbuckle.booking.bookingInitializer
import com.ianarbuckle.booking.BookingNavigator
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.ianarbuckle.database.client.DatabaseClient
import com.ianarbuckle.seathelper.home.HomeActivity
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Ian Arbuckle on 2019-08-25.
 *
 */
class BookingInitialiser(private val baseUrl: String, private val okHttpClient: OkHttpClient, private val navigator: BookingNavigator,
                         private val country: String, private val databaseClient: DatabaseClient, private val deviceUuidFactory: DeviceUuidFactory) {


    fun init() = bookingInitializer {
        withBaseUrl(baseUrl)
        withOkHttpClient(okHttpClient)
        withConverterFactory(MoshiConverterFactory.create())
        withNavigator(navigator)
        withCountry(country)
        withDatabaseClient(databaseClient)
        withDeviceUuidFactory(deviceUuidFactory)
        withCallback {
            val intent = Intent(it, HomeActivity::class.java)
            intent.putExtra("bookingPosition", 1)
            it.startActivity(intent)
        }
    }
}