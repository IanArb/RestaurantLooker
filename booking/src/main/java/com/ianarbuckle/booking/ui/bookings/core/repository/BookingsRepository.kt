package com.ianarbuckle.booking.ui.bookings.core.repository

import com.ianarbuckle.booking.network.manager.BookingServiceManager
import com.ianarbuckle.models.booking.Booking

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingsRepository {

    suspend fun retrieveBookingsByUuid(uuid: String): MutableList<Booking>
}

class BookingsRepositoryImpl(private val serviceManager: BookingServiceManager) : BookingsRepository {

    override suspend fun retrieveBookingsByUuid(uuid: String): MutableList<Booking> = serviceManager.getBookingService().retrieveBookingsByUUID(uuid)
}