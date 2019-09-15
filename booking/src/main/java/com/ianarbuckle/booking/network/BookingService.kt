package com.ianarbuckle.booking.network

import com.ianarbuckle.models.booking.Booking
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingService {

    @GET("/booking/{uuid}")
    suspend fun retrieveBookingsByUUID(@Path("uuid") uuid: String): MutableList<Booking>

    @POST("/booking")
    suspend fun saveBooking(@Body booking: Booking)
}