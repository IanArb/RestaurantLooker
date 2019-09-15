package com.ianarbuckle.booking.ui.bookings.core.interactor

import com.ianarbuckle.booking.ui.bookings.core.repository.BookingsRepository
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.ianarbuckle.models.booking.Booking

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingsInteractor {

    suspend fun getBookings(): MutableList<Booking>
}

class BookingsInteractorImpl(private val repository: BookingsRepository, private val uuidFactory: DeviceUuidFactory) : BookingsInteractor {

    override suspend fun getBookings(): MutableList<Booking> = uuidFactory.getUUID()?.let { repository.retrieveBookingsByUuid(it) }!!

}