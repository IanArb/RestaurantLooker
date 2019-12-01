package com.ianarbuckle.booking.network

import com.ianarbuckle.models.booking.Booking
import retrofit2.http.*

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingService {

    @GET("/booking/{uuid}")
    suspend fun retrieveBookingsByUUID(@Path("uuid") uuid: String): MutableList<Booking>

    @POST("/booking")
    suspend fun saveBooking(@Body booking: Booking)

    @DELETE("/booking/{id}")
    suspend fun deleteBookingById(@Path("id") id: String)
}