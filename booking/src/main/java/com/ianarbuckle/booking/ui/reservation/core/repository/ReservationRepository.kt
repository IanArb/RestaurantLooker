package com.ianarbuckle.booking.ui.reservation.core.repository

import com.ianarbuckle.booking.network.manager.BookingServiceManager

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface ReservationRepository {

}

class ReservationRepositoryImpl(private val serviceManager: BookingServiceManager) : ReservationRepository {

}