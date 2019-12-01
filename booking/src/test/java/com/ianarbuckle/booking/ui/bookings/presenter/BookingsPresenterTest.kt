package com.ianarbuckle.booking.ui.bookings.presenter

import com.ianarbuckle.booking.ui.bookings.core.interactor.BookingsInteractor
import com.ianarbuckle.booking.ui.bookings.core.presenter.BookingsPresenter
import com.ianarbuckle.booking.ui.bookings.core.presenter.BookingsPresenterImpl
import com.ianarbuckle.booking.ui.bookings.core.router.BookingsRouter
import com.ianarbuckle.booking.ui.bookings.core.view.BookingsView
import com.ianarbuckle.models.booking.*
import com.ianarbuckle.models.restaurant.Location
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by Ian Arbuckle on 2019-09-13.
 *
 */
class BookingsPresenterTest {

    @Mock
    lateinit var interactor: BookingsInteractor

    @Mock
    lateinit var view: BookingsView

    @Mock
    lateinit var router: BookingsRouter

    lateinit var presenter: BookingsPresenter

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.IO)
        initMocks(this)

        presenter = BookingsPresenterImpl(view, interactor, router)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `verify that onCreate should show bookings`() {
        runBlocking {
            launch {
                val bookings = ArrayList<Booking>()
                val owner = Owner("1234-1234-1234", "Ian Arbuckle", "ian@mail.com", PhoneNumber(353, 123456789), false, "10/20/2020", "10:00")
                val characteristics = TableCharacteristics("COUPLE", 2, false)
                val table = Table("1", "RESERVED", characteristics)
                val detail = RestaurantDetails("", "", "", Location(0.5f, 0.5f))
                val booking = Booking(owner, detail, table)
                bookings.add(booking)

                whenever(interactor.getBookings()).thenReturn(bookings)

                presenter.onCreate()

                withContext(Dispatchers.Main) {
                    verify(view, times(1)).showLoading()
                    verify(view, times(1)).showBookings(bookings)
                    verify(view, times(1)).hideLoading()
                }
            }
        }
    }

}