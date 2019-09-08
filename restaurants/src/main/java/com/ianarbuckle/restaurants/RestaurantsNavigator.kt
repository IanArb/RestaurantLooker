package com.ianarbuckle.restaurants

import android.content.Context
import com.ianarbuckle.restaurants.data.Restaurant

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface RestaurantsNavigator {
    fun navigateToBookingReservation(context: Context, restaurantName: String)
}