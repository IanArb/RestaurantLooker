package com.ianarbuckle.navigation.booking

import android.app.Activity
import android.content.Intent
import com.ianarbuckle.booking.BookingNavigator

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
class BookingCoordinator : BookingNavigator {

    override fun navigateToBookings(activity: Activity) {
        activity.finish()
    }

    override fun navigateBackToRestaurants(activity: Activity) {
        activity.finish()
    }

    override fun navigateToBookingWithReservation(activity: Activity, destination: Class<out Activity>) {
        val intent = Intent(activity, destination)
        intent.putExtra("bookingPosition", 1)
        activity.startActivity(intent)
    }


}