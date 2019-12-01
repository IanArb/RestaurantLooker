package com.ianarbuckle.booking.ui.booking.core.interactor

import com.ianarbuckle.booking.network.repository.BookingsRepository
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.ianarbuckle.models.booking.Booking

interface BookingInteractor {
    suspend fun fetchBooking(): Booking?
}

class BookingInteractorImpl(private val repository: BookingsRepository) : BookingInteractor {

    override suspend fun fetchBooking(): Booking? = repository.retrieveBookingsByUuid()?.first()
}