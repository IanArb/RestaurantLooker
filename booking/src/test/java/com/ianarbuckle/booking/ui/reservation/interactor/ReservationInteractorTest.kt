package com.ianarbuckle.booking.ui.reservation.interactor

import android.app.Activity
import android.content.Intent
import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.booking.ui.reservation.core.interactor.ReservationInteractor
import com.ianarbuckle.booking.ui.reservation.core.interactor.ReservationInteractorImpl
import com.ianarbuckle.booking.ui.reservation.core.repository.ReservationRepository
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.ianarbuckle.core.utils.FormFieldValidator
import com.ianarbuckle.models.booking.*
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.mockk.every
import io.mockk.mockkObject
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by Ian Arbuckle on 2019-09-08.
 *
 */
class ReservationInteractorTest {

    @Mock
    lateinit var repository: ReservationRepository

    @Mock
    lateinit var activity: Activity

    @Mock
    lateinit var intent: Intent

    @Mock
    lateinit var deviceUuidFactory: DeviceUuidFactory

    @Mock
    lateinit var uuid: UUID

    lateinit var interactor: ReservationInteractor

    @Before
    fun setup() {
        initMocks(this)

        interactor = ReservationInteractorImpl(activity, repository, "IE", deviceUuidFactory)
    }

    @Test
    fun `verify that restaurant name is not empty nor null`() {
        whenever(activity.intent).thenReturn(intent)
        whenever(intent.getStringExtra("NAME")).thenReturn("Buckle's Restaurant")

        assertThat(interactor.getRestaurantsName()).isNotEmpty()
        assertThat(interactor.getRestaurantsName()).isNotNull()
        assertThat(interactor.getRestaurantsName()).isEqualTo("Buckle's Restaurant")
    }

    @Test
    fun `verify that full name validation should return true`() {
        assertThat(interactor.isFullnameValid("Ian")).isTrue()
    }

    @Test
    fun `verify that full name validation should return false`() {
        assertThat(interactor.isFullnameValid("")).isFalse()
    }

    @Test
    fun `verify that email validation should return true`() {
        mockkObject(FormFieldValidator)
        every {
            FormFieldValidator.validEmailAddress("ian@mail.com")
        } returns Result.success("Success")
        assertThat(interactor.isEmailValid("ian@mail.com")).isTrue()
    }

    @Test
    fun `verify that phone number validation should return true`() {
        assertThat(interactor.isPhoneNumberValid("080980809")).isTrue()
    }

    @Test
    fun `verify that phone number validation should return false`() {
        assertThat(interactor.isPhoneNumberValid("")).isFalse()
    }

    @Test
    fun `verify that booking date validation should return true`() {
        assertThat(interactor.isBookingDateValid("Wed 19th, Jul 2019")).isTrue()
    }

    @Test
    fun `verify that booking date validation should return false`() {
        assertThat(interactor.isBookingDateValid("")).isFalse()
    }

    @Test
    fun `verify that arrival time validation should return true`() {
        assertThat(interactor.isArrivalTimeValid("10:00")).isTrue()
    }

    @Test
    fun `verify that arrival time validation should return false`() {
        assertThat(interactor.isArrivalTimeValid("")).isFalse()
    }

    @Test
    fun `verify booking properties are populated and are not empty nor null`() {
        whenever(activity.intent).thenReturn(intent)
        whenever(intent.getStringExtra("NAME")).thenReturn("Buckle's")
        whenever(deviceUuidFactory.getUUID()).thenReturn("1234-1234-1234")
        val owner = Owner("1234-1234-1234", "Ian Arbuckle", "ian@mail.com", PhoneNumber(353, 123456789), false, "10/20/2020", "10:00")
        val characteristics = TableCharacteristics("COUPLE", 2, false)
        val table = Table("1", "RESERVED", characteristics)
        val booking = Booking(owner, "Buckle's", table)

        val properties = HashMap<String, String>()
        properties.apply {
            put("email", "ian@mail.com")
            put("surname", "Ian Arbuckle")
            put("phoneNumber", "90909090")
            put("bookingDate", "10/20/2020")
            put("arrivalTime", "10:00")
            put("dietaryRequirements", "false")
        }

        assertThat(interactor.createBookingRequest(properties)).isEqualTo(booking)

    }

    @Test
    fun `verify that booking is created and is not empty nor null`() {
        val owner = Owner("1234-1234-1234", "Ian Arbuckle", "ian@mail.com", PhoneNumber(353, 123456789), false, "10/20/2020", "10:00")
        val characteristics = TableCharacteristics("COUPLE", 2, false)
        val table = Table("1", "RESERVED", characteristics)
        val booking = Booking(owner, "Buckle's", table)

        runBlocking {
            interactor.saveBooking(booking)
            verify(repository, times(1)).saveBooking(booking)
        }
    }

}