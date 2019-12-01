package com.ianarbuckle.booking.ui.reservation.presenter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.ianarbuckle.booking.ui.reservation.core.interactor.ReservationInteractor
import com.ianarbuckle.booking.ui.reservation.core.presenter.ReservationPresenter
import com.ianarbuckle.booking.ui.reservation.core.presenter.ReservationPresenterImpl
import com.ianarbuckle.booking.ui.reservation.core.router.ReservationRouter
import com.ianarbuckle.booking.ui.reservation.core.view.ReservationView
import com.ianarbuckle.models.booking.*
import com.ianarbuckle.models.restaurant.Location
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by Ian Arbuckle on 2019-09-08.
 *
 */
class ReservationPresenterTest {

    @Mock
    lateinit var view: ReservationView

    @Mock
    lateinit var interactor: ReservationInteractor

    @Mock
    lateinit var router: ReservationRouter

    @Mock
    lateinit var activity: Activity

    @Mock
    lateinit var bundle: Bundle

    @Mock
    lateinit var intent: Intent

    lateinit var presenter: ReservationPresenter

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.IO)
        initMocks(this)

        presenter = ReservationPresenterImpl(view, interactor, router)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `verify that onCreate should set phone prefix value`() {
        whenever(interactor.getPhonePrefix()).thenReturn("353")

        presenter.onCreate()

        verify(view, times(1)).setPrefixValue("353")
    }

    @Test
    fun `verify that onCreate on toolbar click should navigate back`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).onToolbarBackClickListener(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        verify(router, times(1)).navigateBack()
    }

    @Test
    fun `verify that onCreate on booking date field click it should navigate to calendar picker`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).onBookingDateClickListener(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        verify(router, times(1)).navigateToCalendarPicker()
    }

    @Test
    fun `verify that onCreate on arrival time date field click it should show time picker`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).onArrivalTimeClickListener(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        verify(view, times(1)).showDateTimePicker()
    }

    @Test
    fun `verify that onCreate on phone prefix field click it should navigate to phone prefix picker`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).onPrefixClickListener(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        verify(router, times(1)).navigateToPrefixPicker()
    }

    @Test
    fun `verify that onCreate on continue button click it should show invalid email message`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).onContinueButtonClick(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        whenever(interactor.isEmailValid("iarbuckle@")).thenReturn(false)

        verify(view, times(1)).showEmailFieldError()
    }

    @Test
    fun `verify that onCreate on continue click it should show invalid phone number message`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).onContinueButtonClick(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        whenever(interactor.isPhoneNumberValid("")).thenReturn(false)

        verify(view, times(1)).showPhoneNumberFieldError()
    }

    @Test
    fun `verify that onCreate on continue click it should show invalid fullname message`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).onContinueButtonClick(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        whenever(interactor.isFullnameValid("")).thenReturn(false)

        verify(view, times(1)).showFullNameFieldError()
    }

    @Test
    fun `verify that onCreate on continue click it should show invalid booking date message`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).onContinueButtonClick(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        whenever(interactor.isBookingDateValid("")).thenReturn(false)

        verify(view, times(1)).showBookingDateFieldError()
    }

    @Test
    fun `verify that onCreate on continue it should show invalid arrival date message`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).onContinueButtonClick(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        whenever(interactor.isArrivalTimeValid("")).thenReturn(false)

        verify(view, times(1)).showArrivalTimeFieldError()
    }

    @Test
    fun `verify that onCreate onActivityResult it should set booking date value`() {
        whenever(activity.intent).thenReturn(intent)
        whenever(interactor.getDataFromActivityResult(100, Activity.RESULT_OK, intent)).thenReturn("12/13")

        presenter.onActivityResult(100, Activity.RESULT_OK, intent)

        verify(view, times(1)).setBookingDatesValue(anyString())
    }

    @Test
    fun `verify that onCreate onActivityResult it should set phone prefix value`() {
        whenever(activity.intent).thenReturn(intent)
        whenever(interactor.getDataFromActivityResult(200, Activity.RESULT_OK, intent)).thenReturn("353")

        presenter.onActivityResult(200, Activity.RESULT_OK, intent)

        verify(view, times(1)).setPrefixValue(anyString())
    }

    @Test
    fun `verify that onSavedInstanceState it should populate form field values`() {
        whenever(activity.intent).thenReturn(intent)
        whenever(intent.getBundleExtra("")).thenReturn(bundle)

        val bundle = Bundle().apply {
            putString("name", "Buckle")
            putString("email", "buckle@mail.com")
            putString("phone", "123456789")
            putString("bookingDate", "12/13/2018")
            putString("arrivalTime", "17:00")
            putBoolean("diet", false)
        }

        presenter.onSavedInstanceState(bundle)

        verify(view, times(1)).getFullNameValue()
        verify(view, times(1)).getEmailValue()
        verify(view, times(1)).getPhoneNumberValue()
        verify(view, times(1)).getBookingDatesValue()
        verify(view, times(1)).getArrivalTimeValue()
        verify(view, times(1)).getDietaryRequirementsValue()
    }

    @Test
    fun `verify that onCreate it should show restaurant name`() {
        whenever(interactor.getRestaurantsName()).thenReturn("Buckle's")

        presenter.onCreate()

        verify(view, times(1)).showRestaurantName(anyString())
    }

    @Test
    fun `verify that onCreate it should attach text watchers`() {
        presenter.onCreate()

        verify(view, times(1)).apply {
            emailFieldTextWatcher()
            phoneFieldTextWatcher()
            fullnameTextWatcher()
            bookingDateTextWatcher()
            arrivalTimeDateTextWatcher()
        }
    }

}