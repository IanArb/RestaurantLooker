package com.ianarbuckle.navigation.restaurants

import android.content.Context
import android.content.Intent
import com.ianarbuckle.booking.ui.reservation.ReservationActivity
import com.ianarbuckle.models.restaurant.Restaurant
import com.ianarbuckle.navigation.Constants.RESTAURANT
import com.ianarbuckle.restaurants.RestaurantsNavigator

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
class RestaurantsCoordinator : RestaurantsNavigator {

    override fun navigateToBookingReservation(context: Context, restaurant: Restaurant) {
        val intent = Intent(context, ReservationActivity::class.java)
        intent.putExtra(RESTAURANT, restaurant)
        context.startActivity(intent)
    }
}