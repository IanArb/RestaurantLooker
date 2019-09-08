package com.ianarbuckle.booking.ui.reservation.core.presenter

import android.os.Bundle
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
    fun onSavedInstanceState(bundle: Bundle)
    fun onRestoreInstanceState(bundle: Bundle)
}

class ReservationPresenterImpl(private val view: ReservationView, private val interactor: ReservationInteractor, private val router: ReservationRouter) : ReservationPresenter, CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreate() {
        view.apply {
            attachClickListeners()
            showRestaurantName(interactor.getRestaurantsName())
            attachTextWatchers()
            setPrefixValue(interactor.getPhonePrefix())
        }
    }

    override fun onSavedInstanceState(bundle: Bundle) {
        bundle.putString("name", view.getFullNameValue())
        bundle.putString("email", view.getEmailValue())
        bundle.putString("phone", view.getPhoneNumberValue())
        bundle.putString("bookingDate", view.getBookingDatesValue())
        bundle.putString("arrivalTime", view.getArrivalTimeValue())
        bundle.putBoolean("diet", view.getDietaryRequirementsValue())
    }

    override fun onRestoreInstanceState(bundle: Bundle) {
        view.setFullNameValue(bundle.getString("name"))
        view.setEmailValue(bundle.getString("email"))
        view.setPhoneNumberValue(bundle.getString("phone"))
        view.setArrivalTimeValue(bundle.getString("arrivalTime"))
        view.setBookingDatesValue(bundle.getString("bookingDate"))
        view.setDietaryRequirementValue(bundle.getBoolean("diet"))
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
//                createReservation()
            }
        }
    }

    private fun createReservation() {
        val properties = createBookingProperties()

        job = launch {
            view.showLoading()
            try {
                interactor.saveBooking(interactor.createBookingRequest(properties))
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    view.showErrorDialog()
                }
            } finally {
                router.navigateToConfirmation()
            }
        }
    }

    private fun createBookingProperties(): HashMap<String, String> {
        val properties = HashMap<String, String>()
        properties.apply {
            put("email", view.getEmailValue())
            put("surname", view.getFullNameValue())
            put("phoneNumber", view.getPhoneNumberValue())
            put("bookingDate", view.getBookingDatesValue())
            put("arrivalTime", view.getArrivalTimeValue())
            put("dietaryRequirements", view.getDietaryRequirementsValue().toString())
        }
        return properties
    }

    private fun hasValidFields(): Boolean {
        return interactor.isEmailValid(view.getEmailValue()) && interactor.isFullnameValid(view.getFullNameValue())
                && interactor.isPhoneNumberValid(view.getPhoneNumberValue())
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