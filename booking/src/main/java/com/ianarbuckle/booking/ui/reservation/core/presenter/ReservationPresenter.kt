package com.ianarbuckle.booking.ui.reservation.core.presenter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.ianarbuckle.booking.ui.reservation.constants.Constants.ARRIVAL_TIME_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.BOOKING_DATE_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.DIET_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.EMAIL_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.NAME_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.PHONE_CODE_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.PHONE_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.REQUEST_CODE_CALENDAR
import com.ianarbuckle.booking.ui.reservation.constants.Constants.REQUEST_CODE_PREFIX
import com.ianarbuckle.booking.ui.reservation.core.interactor.ReservationInteractor
import com.ianarbuckle.booking.ui.reservation.core.router.ReservationRouter
import com.ianarbuckle.booking.ui.reservation.core.view.ReservationView
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface ReservationPresenter {
    fun onCreate()
    fun onDestroy()
    fun onSavedInstanceState(bundle: Bundle?)
    fun onRestoreInstanceState(bundle: Bundle?)
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}

class ReservationPresenterImpl(private val view: ReservationView, private val interactor: ReservationInteractor, private val router: ReservationRouter) : ReservationPresenter, CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreate() {
        view.apply {
            setPrefixValue(interactor.getPhonePrefix())
            attachClickListeners()
            if(!interactor.getRestaurantsName().isNullOrEmpty()) {
                showRestaurantName(interactor.getRestaurantsName())
            }
            attachTextWatchers()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val value = interactor.getDataFromActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_CODE_CALENDAR -> {
                if(!value.isNullOrEmpty()) {
                    view.setBookingDatesValue(value)
                }
            }
            REQUEST_CODE_PREFIX -> {
                if(!value.isNullOrEmpty()) {
                    view.setPrefixValue(value)
                }
            }
        }
    }

    override fun onSavedInstanceState(bundle: Bundle?) {
        bundle?.apply {
            view.apply {
                putString(NAME_KEY, getFullNameValue())
                putString(EMAIL_KEY, getEmailValue())
                putString(PHONE_KEY, getPhoneNumberValue())
                putString(BOOKING_DATE_KEY, getBookingDatesValue())
                putString(ARRIVAL_TIME_KEY, getArrivalTimeValue())
                putBoolean(DIET_KEY, getDietaryRequirementsValue())
            }
        }
    }

    override fun onRestoreInstanceState(bundle: Bundle?) {
        bundle?.apply {
            view.apply {
                setFullNameValue(getString(NAME_KEY))
                setEmailValue(getString(EMAIL_KEY))
                setPhoneNumberValue(getString(PHONE_KEY))
                setArrivalTimeValue(getString(ARRIVAL_TIME_KEY))
                setBookingDatesValue(getString(BOOKING_DATE_KEY))
                setDietaryRequirementValue(getBoolean(DIET_KEY))
            }
        }
    }

    private fun ReservationView.attachTextWatchers() {
        emailFieldTextWatcher()
        phoneFieldTextWatcher()
        fullnameTextWatcher()
        bookingDateTextWatcher()
        arrivalTimeDateTextWatcher()
    }

    private fun ReservationView.attachClickListeners() {
        onToolbarBackClickListener {
            router.navigateBack()
        }
        onArrivalTimeClickListener {
            showDateTimePicker()
        }
        onBookingDateClickListener {
            router.navigateToCalendarPicker()
        }
        onPrefixClickListener {
            router.navigateToPrefixPicker()
        }
        onContinueButtonClick {
            validateFields()
            if (hasValidFields()) {
                createReservation()
            }
        }
    }

    private fun createReservation() {
        val properties = createBookingProperties()

        job = launch {
            withContext(Dispatchers.Main) {
                view.showLoading()
            }
            try {
                val booking = interactor.createBookingRequest(properties)
                interactor.saveBooking(booking)
                withContext(Dispatchers.Main) {
                    router.navigateToConfirmation(booking)
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                    view.showErrorMessage()
                    Toast.makeText(view.getView().context, "${exception.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            } finally {
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                }
            }
        }
    }

    private fun createBookingProperties(): HashMap<String, String> {
        val properties = HashMap<String, String>()
        properties.apply {
            put(EMAIL_KEY, view.getEmailValue())
            put(NAME_KEY, view.getFullNameValue())
            put(PHONE_KEY, view.getPhoneNumberValue())
            put(PHONE_CODE_KEY, view.getPrefixValue())
            put(BOOKING_DATE_KEY, view.getBookingDatesValue())
            put(ARRIVAL_TIME_KEY, view.getArrivalTimeValue())
            put(DIET_KEY, view.getDietaryRequirementsValue().toString())
        }
        return properties
    }

    private fun hasValidFields(): Boolean {
        return interactor.isEmailValid(view.getEmailValue())
                && interactor.isFullnameValid(view.getFullNameValue())
                && interactor.isPhoneNumberValid(view.getPhoneNumberValue())
                && interactor.isBookingDateValid(view.getBookingDatesValue())
                && interactor.isArrivalTimeValid(view.getArrivalTimeValue())
    }

    private fun validateFields() {
        if(!interactor.isEmailValid(view.getEmailValue())) {
            view.showEmailFieldError()
        }

        if(!interactor.isPhoneNumberValid(view.getPhoneNumberValue())) {
            view.showPhoneNumberFieldError()
        }

        if(!interactor.isFullnameValid(view.getFullNameValue())) {
            view.showFullNameFieldError()
        }

        if(!interactor.isBookingDateValid(view.getBookingDatesValue())) {
            view.showBookingDateFieldError()
        }

        if(!interactor.isArrivalTimeValid(view.getArrivalTimeValue())) {
            view.showArrivalTimeFieldError()
        }
    }

    override fun onDestroy() {
        job.cancel()
    }
}