package com.ianarbuckle.booking.ui.reservation.core.router

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ianarbuckle.booking.BookingNavigator
import com.ianarbuckle.booking.R
import com.ianarbuckle.booking.ui.calendar.CalendarActivity
import com.ianarbuckle.booking.ui.confirmation.ConfirmationActivity
import com.ianarbuckle.booking.ui.phonePrefix.PhonePrefixActivity
import com.ianarbuckle.booking.ui.reservation.ReservationActivity
import com.ianarbuckle.booking.ui.reservation.constants.Constants.BOOKING_KEY
import com.ianarbuckle.booking.ui.reservation.constants.Constants.REQUEST_CODE_CALENDAR
import com.ianarbuckle.booking.ui.reservation.constants.Constants.REQUEST_CODE_PREFIX
import com.ianarbuckle.models.booking.Booking

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface ReservationRouter {
    fun navigateToConfirmation(booking: Booking)
    fun navigateToCalendarPicker()
    fun navigateToPrefixPicker()
    fun navigateBack()
}

class ReservationRouterImpl(private val activity: ReservationActivity, private val navigator: BookingNavigator) : ReservationRouter {

    override fun navigateToConfirmation(booking: Booking) {
        val intent = Intent(activity, ConfirmationActivity::class.java)
        intent.putExtra(BOOKING_KEY, booking)
        activity.startActivity(intent)
    }

    override fun navigateToCalendarPicker() {
        val intent = Intent(activity, CalendarActivity::class.java)
        activity.startActivityForResult(intent, REQUEST_CODE_CALENDAR)
    }

    override fun navigateToPrefixPicker() {
        val intent = Intent(activity, PhonePrefixActivity::class.java)
        activity.startActivityForResult(intent, REQUEST_CODE_PREFIX)
    }

    override fun navigateBack() {
        navigator.navigateBackToRestaurants(activity)
    }
}