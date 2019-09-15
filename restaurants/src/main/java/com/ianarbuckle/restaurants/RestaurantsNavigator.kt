package com.ianarbuckle.restaurants

import android.content.Context

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface RestaurantsNavigator {
    fun navigateToBookingReservation(context: Context, restaurantName: String)
}