package com.ianarbuckle.restaurants.ui.home.router

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.ianarbuckle.restaurants.RestaurantsNavigator
import com.ianarbuckle.restaurants.data.Restaurant
import com.ianarbuckle.restaurants.ui.menu.MenuActivity
import com.ianarbuckle.restaurants.utils.Constants
import java.util.ArrayList


/**
 * Created by Ian Arbuckle on 20/07/2018.
 *
 */
class DefaultRestaurantsRouter(private val context: Context, private val navigator: RestaurantsNavigator) : RestaurantsRouter {

    override fun onNavigateToBookingScreen(restaurant: Restaurant?) {

    }

    override fun navigateMenu(restaurant: Restaurant) {
        val intent = Intent(context, MenuActivity::class.java)
        intent.putParcelableArrayListExtra(Constants.DISHES_KEY, restaurant.dishes as ArrayList<out Parcelable>)
        intent.putExtra(Constants.RESTAURANT_KEY, restaurant)
        context.startActivity(intent)
    }

    override fun navigateToBookingReservation(restaurant: Restaurant) {
        navigator.navigateToBookingReservation(context, restaurant.restaurantName)
    }

}