package com.ianarbuckle.booking.ui.reservation.core.repository

import com.ianarbuckle.booking.data.Booking
import com.ianarbuckle.booking.network.manager.BookingServiceManager

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface ReservationRepository {
    suspend fun saveBooking(booking: Booking)
}

class ReservationRepositoryImpl(private val serviceManager: BookingServiceManager) : ReservationRepository {

    override suspend fun saveBooking(booking: Booking) = serviceManager.getBookingService().saveBooking(booking)
}