package com.ianarbuckle.booking.network

import com.ianarbuckle.booking.data.Booking
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingService {

    @GET("/booking/")
    fun retrieveBookingsByUUID(@Path("uuid") uuid: String): MutableList<Booking>

}