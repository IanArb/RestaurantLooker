package com.ianarbuckle.booking.network.repository

import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.booking.network.BookingService
import com.ianarbuckle.booking.network.manager.BookingServiceManager
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.ianarbuckle.models.booking.*
import com.ianarbuckle.models.restaurant.Location
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by Ian Arbuckle on 2019-09-13.
 *
 */
class BookingsRepositoryTest {

    @Mock
    lateinit var serviceManager: BookingServiceManager

    @Mock
    lateinit var bookingService: BookingService

    @Mock
    lateinit var uuidFactory: DeviceUuidFactory

    lateinit var repository: BookingsRepository

    @Before
    fun setup() {
        initMocks(this)

        repository = BookingsRepositoryImpl(serviceManager, uuidFactory)
    }

    @Test
    fun `verify that retrieving bookings by UUID is not empty nor null`() {
        runBlocking {
            val bookings = ArrayList<Booking>()
            val owner = Owner("1234-1234-1234", "Ian Arbuckle", "ian@mail.com", PhoneNumber(353, 123456789), false, "10/20/2020", "10:00")
            val characteristics = TableCharacteristics("COUPLE", 2, false)
            val table = Table("1", "RESERVED", characteristics)
            val details = RestaurantDetails("Buckle's", "image", "Buckle Town", Location(0.5f, 0.5f))
            val booking = Booking(owner, details, table)
            bookings.add(booking)
            whenever(uuidFactory.getUUID()).thenReturn("1234-1234-1234")
            whenever(serviceManager.getBookingService()).thenReturn(bookingService)
            whenever(bookingService.retrieveBookingsByUUID("1234-1234-1234")).thenReturn(bookings)

            assertThat(repository.retrieveBookingsByUuid()).isNotEmpty()
            verify(serviceManager, times(1)).getBookingService()
        }
    }
}