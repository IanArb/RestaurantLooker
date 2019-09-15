package com.ianarbuckle.navigation.restaurants

import android.content.Context
import android.content.Intent
import com.ianarbuckle.booking.ui.reservation.ReservationActivity
import com.ianarbuckle.navigation.Constants.RESTAURANTS_NAME_KEY
import com.ianarbuckle.restaurants.RestaurantsNavigator

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
class RestaurantsCoordinator : RestaurantsNavigator {

    override fun navigateToBookingReservation(context: Context, restaurantName: String) {
        val intent = Intent(context, ReservationActivity::class.java)
        intent.putExtra(RESTAURANTS_NAME_KEY, restaurantName)
        context.startActivity(intent)
    }
}