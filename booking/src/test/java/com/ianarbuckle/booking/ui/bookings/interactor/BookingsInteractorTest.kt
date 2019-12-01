package com.ianarbuckle.booking.ui.bookings.interactor

import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.booking.ui.bookings.core.interactor.BookingsInteractor
import com.ianarbuckle.booking.ui.bookings.core.interactor.BookingsInteractorImpl
import com.ianarbuckle.booking.network.repository.BookingsRepository
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.ianarbuckle.models.booking.*
import com.ianarbuckle.models.restaurant.Location
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
class BookingsInteractorTest {

    @Mock
    lateinit var repository: BookingsRepository

    @Mock
    lateinit var uuidFactory: DeviceUuidFactory

    lateinit var interactor: BookingsInteractor

    @Before
    fun setup() {
        initMocks(this)

        interactor = BookingsInteractorImpl(repository, uuidFactory)
    }

    @Test
    fun `verify that retrieve bookings by UUID from repository should return bookings that are not empty nor null`() {
        runBlocking {
            val bookings = ArrayList<Booking>()
            val owner = Owner("1234-1234-1234", "Ian Arbuckle", "ian@mail.com", PhoneNumber(353, 123456789), false, "10/20/2020", "10:00")
            val characteristics = TableCharacteristics("COUPLE", 2, false)
            val table = Table("1", "RESERVED", characteristics)
            val details = RestaurantDetails("Buckle's", "image", "Buckle Town", Location(0.5f, 0.5f))
            val booking = Booking(owner, details, table)
            bookings.add(booking)

            whenever(uuidFactory.getUUID()).thenReturn("1234-1234-1234")
            whenever(repository.retrieveBookingsByUuid()).thenReturn(bookings)

            assertThat(interactor.getBookings()).isNotEmpty()
        }
    }

    @Test
    fun `verify that retrieve bookings by UUID from repository should return bookings greater than 1`() {
        runBlocking {
            val bookings = ArrayList<Booking>()
            val owner = Owner("1234-1234-1234", "Ian Arbuckle", "ian@mail.com", PhoneNumber(353, 123456789), false, "10/20/2020", "10:00")
            val characteristics = TableCharacteristics("COUPLE", 2, false)
            val table = Table("1", "RESERVED", characteristics)
            val details = RestaurantDetails("Buckle's", "image", "Buckle Town", Location(0.5f, 0.5f))
            val booking = Booking(owner, details, table)
            val booking2 = Booking(owner, details, table)

            bookings.apply {
                add(booking)
                add(booking2)
            }

            whenever(uuidFactory.getUUID()).thenReturn("1234-1234-1234")
            whenever(repository.retrieveBookingsByUuid()).thenReturn(bookings)

            assertThat(interactor.getBookings()).hasSize(2)
        }
    }




}