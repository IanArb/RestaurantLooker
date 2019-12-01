package com.ianarbuckle.restaurants

import android.content.Context
import com.ianarbuckle.models.restaurant.Restaurant

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface RestaurantsNavigator {
    fun navigateToBookingReservation(context: Context, restaurant: Restaurant)
}