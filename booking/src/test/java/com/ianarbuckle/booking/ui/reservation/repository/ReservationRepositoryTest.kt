package com.ianarbuckle.booking.ui.reservation.repository

import com.ianarbuckle.booking.network.BookingService
import com.ianarbuckle.booking.network.manager.BookingServiceManager
import com.ianarbuckle.booking.ui.reservation.core.repository.ReservationRepository
import com.ianarbuckle.booking.ui.reservation.core.repository.ReservationRepositoryImpl
import com.ianarbuckle.models.booking.*
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by Ian Arbuckle on 2019-09-08.
 *
 */
class ReservationRepositoryTest {

    @Mock
    lateinit var serviceManager: BookingServiceManager

    @Mock
    lateinit var bookingService: BookingService

    lateinit var repository: ReservationRepository

    @Before
    fun setup() {
        initMocks(this)

        repository = ReservationRepositoryImpl(serviceManager)
    }

    @Test
    fun `verify that repository calls service manager to retrieve save booking request`() {
        val owner = Owner("1234-1234-1234", "Ian Arbuckle", "ian@mail.com", PhoneNumber(353, 123456789), false, "10/20/2020", "10:00")
        val characteristics = TableCharacteristics("COUPLE", 2, false)
        val table = Table("1", "RESERVED", characteristics)
        val booking = Booking(owner, "Buckle's", table)

        runBlocking {
            whenever(serviceManager.getBookingService()).thenReturn(bookingService)

            repository.saveBooking(booking)

            verify(serviceManager, times(1)).getBookingService()
            verify(bookingService, times(1)).saveBooking(booking)
        }
    }
}