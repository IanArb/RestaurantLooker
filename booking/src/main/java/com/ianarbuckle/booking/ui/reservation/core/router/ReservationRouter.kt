package com.ianarbuckle.booking.ui.reservation.core.router

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ianarbuckle.booking.BookingNavigator
import com.ianarbuckle.booking.R
import com.ianarbuckle.booking.ui.calendar.CalendarActivity
import com.ianarbuckle.booking.ui.phonePrefix.PhonePrefixActivity
import com.ianarbuckle.booking.ui.reservation.ReservationActivity

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface ReservationRouter {
    fun navigateToBookings()
    fun navigateToConfirmation()
    fun navigateToCalendarPicker()
    fun navigateToPrefixPicker()
    fun navigateBack()
}

class ReservationRouterImpl(private val activity: ReservationActivity, private val navigator: BookingNavigator) : ReservationRouter {

    override fun navigateToBookings() {
        navigator.navigateToBookings(activity)
    }

    override fun navigateToConfirmation() {

    }

    override fun navigateToCalendarPicker() {
        val intent = Intent(activity, CalendarActivity::class.java)
        activity.startActivity(intent)
    }

    override fun navigateToPrefixPicker() {
        val intent = Intent(activity, PhonePrefixActivity::class.java)
        activity.startActivity(intent)
    }

    override fun navigateBack() {
        navigator.navigateBackToRestaurants(activity)
    }
}