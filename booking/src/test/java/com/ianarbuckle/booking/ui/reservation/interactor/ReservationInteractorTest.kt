package com.ianarbuckle.booking.ui.reservation.interactor

import android.app.Activity
import android.content.Intent
import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.booking.network.repository.BookingsRepository
import com.ianarbuckle.booking.ui.reservation.core.interactor.ReservationInteractor
import com.ianarbuckle.booking.ui.reservation.core.interactor.ReservationInteractorImpl
import com.ianarbuckle.booking.ui.reservation.core.repository.ReservationRepository
import com.ianarbuckle.booking.utils.TestData
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.ianarbuckle.core.utils.FormFieldValidator
import com.ianarbuckle.models.booking.*
import com.ianarbuckle.models.restaurant.Location
import com.ianarbuckle.models.restaurant.Restaurant
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
    lateinit var repository: BookingsRepository

    @Mock
    lateinit var activity: Activity

    @Mock
    lateinit var intent: Intent

    @Mock
    lateinit var deviceUuidFactory: DeviceUuidFactory

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
        whenever(intent.getParcelableExtra<Restaurant>("RESTAURANT")).thenReturn(TestData.createRestaurant())
        whenever(deviceUuidFactory.getUUID()).thenReturn("1234-1234-1234")
        val owner = Owner("1234-1234-1234", "Ian Arbuckle", "ian@mail.com", PhoneNumber(353, 123456789), false, "10/20/2020", "10:00")
        val characteristics = TableCharacteristics("COUPLE", 2, false)
        val table = Table("1", "RESERVED", characteristics)
        val details = RestaurantDetails("Buckle's", "imageUrl", "Dublin", Location(0.5f, 0.5f))
        val booking = Booking(owner, details, table)

        val properties = HashMap<String, String>()
        properties.apply {
            put("EMAIL_KEY", "ian@mail.com")
            put("NAME_KEY", "Ian Arbuckle")
            put("PHONE_CODE_KEY", "353")
            put("PHONE_KEY", "123456789")
            put("BOOKING_DATE_KEY", "10/20/2020")
            put("ARRIVAL_TIME_KEY", "10:00")
            put("DIET_KEY", "false")
        }

        assertThat(interactor.createBookingRequest(properties)).isEqualTo(booking)

    }

    @Test
    fun `verify that booking is created and is not empty nor null`() {
        val owner = Owner("1234-1234-1234", "Ian Arbuckle", "ian@mail.com", PhoneNumber(353, 123456789), false, "10/20/2020", "10:00")
        val characteristics = TableCharacteristics("COUPLE", 2, false)
        val table = Table("1", "RESERVED", characteristics)
        val details = RestaurantDetails("Buckle's", "image", "Buckle Town", Location(0.5f, 0.5f))
        val booking = Booking(owner, details, table)

        runBlocking {
            interactor.saveBooking(booking)
            verify(repository, times(1)).saveBooking(booking)
        }
    }

}