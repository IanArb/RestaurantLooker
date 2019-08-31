package com.ianarbuckle.booking.ui.bookings.core.repository

import com.ianarbuckle.booking.network.manager.BookingServiceManager

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingRepository {

}

class BookingRepositoryImpl(private val serviceManager: BookingServiceManager) : BookingRepository {

}