package com.ianarbuckle.booking.ui.confirmation.presenter

import com.ianarbuckle.booking.ui.confirmation.interactor.ConfirmationInteractor
import com.ianarbuckle.booking.ui.confirmation.router.ConfirmationRouter
import com.ianarbuckle.booking.ui.confirmation.view.ConfirmationView
import com.ianarbuckle.models.booking.*
import com.ianarbuckle.models.restaurant.Location
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by Ian Arbuckle on 2019-09-12.
 *
 */
class ConfirmationPresenterTest {

    @Mock
    lateinit var interactor: ConfirmationInteractor

    @Mock
    lateinit var router: ConfirmationRouter

    @Mock
    lateinit var view: ConfirmationView

    lateinit var presenter: ConfirmationPresenter

    @Before
    fun setup() {
        initMocks(this)
        presenter = ConfirmationPresenterImpl(view, interactor, router)
    }

    @Test
    fun `verify that onCreate that it should show booking confirmation values`() {
        val owner = Owner("1234-1234-1234", "Ian Arbuckle", "ian@mail.com", PhoneNumber(353, 123456789), false, "10/20/2020", "10:00")
        val characteristics = TableCharacteristics("COUPLE", 2, false)
        val table = Table("1", "RESERVED", characteristics)
        val details = RestaurantDetails("Buckle's", "image", "Buckle Town", Location(0.5f, 0.5f))
        val booking = Booking(owner, details, table)
        whenever(interactor.getBooking()).thenReturn(booking)

        presenter.onCreate()

        verify(view, times(1)).showBooking(booking)
    }

    @Test
    fun `verify that onCreate it should navigate to bookings on button click`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).onBookingsClickListener(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        verify(router, times(1)).navigateToBookings()
    }
}