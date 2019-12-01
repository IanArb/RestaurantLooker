package com.ianarbuckle.booking.network.repository

import com.ianarbuckle.booking.network.manager.BookingServiceManager
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.ianarbuckle.models.booking.Booking

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingsRepository {
    suspend fun retrieveBookingsByUuid(): MutableList<Booking>?
    suspend fun saveBooking(booking: Booking)
    suspend fun deleteBooking(id: String)
}

class BookingsRepositoryImpl(private val serviceManager: BookingServiceManager, private val uuidFactory: DeviceUuidFactory) : BookingsRepository {

    override suspend fun retrieveBookingsByUuid(): MutableList<Booking>? = uuidFactory.getUUID()?.let { serviceManager.getBookingService().retrieveBookingsByUUID(it) }

    override suspend fun saveBooking(booking: Booking) = serviceManager.getBookingService().saveBooking(booking)

    override suspend fun deleteBooking(id: String) = serviceManager.getBookingService().deleteBookingById(id)
}