package com.ianarbuckle.booking

import android.app.Activity

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface BookingNavigator {
    fun navigateToBookings(activity: Activity)
    fun navigateBackToRestaurants(activity: Activity)
    fun navigateToBookingWithReservation(activity: Activity, destination: Class<out Activity>)
}