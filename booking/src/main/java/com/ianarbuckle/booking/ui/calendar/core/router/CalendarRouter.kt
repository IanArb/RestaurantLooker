package com.ianarbuckle.booking.ui.calendar.core.router

import android.app.Activity
import android.content.Intent
import com.ianarbuckle.booking.ui.reservation.ReservationActivity

/**
 * Created by Ian Arbuckle on 2019-09-02.
 *
 */
interface CalendarRouter {
    fun navigateToReservationWithDate(bookingDate: String)
}

class CalendarRouterImpl(private val activity: Activity) : CalendarRouter {

    override fun navigateToReservationWithDate(bookingDate: String) {
        val intent = Intent(activity, ReservationActivity::class.java)
        intent.putExtra("bookingDate", bookingDate)
        activity.startActivity(intent)
    }
}