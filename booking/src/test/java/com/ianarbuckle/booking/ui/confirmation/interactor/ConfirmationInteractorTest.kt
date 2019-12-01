package com.ianarbuckle.booking.ui.confirmation.interactor

import android.app.Activity
import android.content.Intent
import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.models.booking.*
import com.ianarbuckle.models.restaurant.Location
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by Ian Arbuckle on 2019-09-12.
 *
 */
class ConfirmationInteractorTest {

    @Mock
    lateinit var activity: Activity

    @Mock
    lateinit var intent: Intent

    lateinit var interactor: ConfirmationInteractor

    @Before
    fun setup() {
        initMocks(this)
        interactor = ConfirmationInteractorImpl(activity)
    }

    @Test
    fun `verify that booking is not empty nor null`() {
        val owner = Owner("1234-1234-1234", "Ian Arbuckle", "ian@mail.com", PhoneNumber(353, 123456789), false, "10/20/2020", "10:00")
        val characteristics = TableCharacteristics("COUPLE", 2, false)
        val table = Table("1", "RESERVED", characteristics)
        val details = RestaurantDetails("Buckle's", "image", "Buckle Town", Location(0.5f, 0.5f))
        val booking = Booking(owner, details, table)
        whenever(activity.intent).thenReturn(intent)
        whenever(intent.getParcelableExtra<Booking>("BOOKING_KEY")).thenReturn(booking)

        assertThat(interactor.getBooking()).isNotNull()
    }
}