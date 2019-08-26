package com.ianarbuckle.booking.ui.bookings.core.interactor

import com.ianarbuckle.booking.ui.bookings.core.repository.BookingRepository

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingInteractor {

}

class BookingInteractorImpl(private val repository: BookingRepository) : BookingInteractor {

}