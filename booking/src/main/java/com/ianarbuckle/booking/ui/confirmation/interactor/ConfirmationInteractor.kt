package com.ianarbuckle.booking.ui.confirmation.interactor

import android.app.Activity
import com.ianarbuckle.booking.ui.reservation.constants.Constants.BOOKING_KEY
import com.ianarbuckle.models.booking.Booking

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface ConfirmationInteractor {
    fun getBooking(): Booking
}

class ConfirmationInteractorImpl(private val activity: Activity) : ConfirmationInteractor {

    override fun getBooking(): Booking = activity.intent.getParcelableExtra(BOOKING_KEY)

}
