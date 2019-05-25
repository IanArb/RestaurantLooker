package com.ianarbuckle.restaurants.ui.home.router

import android.content.Context
import android.content.Intent
import com.ianarbuckle.restaurants.data.Restaurant
import com.ianarbuckle.restaurants.ui.menu.MenuActivity


/**
 * Created by Ian Arbuckle on 20/07/2018.
 *
 */
class DefaultRestaurantsRouter(private val context: Context?) : RestaurantsRouter {

    override fun onNavigateToBookingScreen(restaurant: Restaurant?) {

    }

    override fun navigateMenu(restaurant: Restaurant) {
        val intent = Intent(this.context, MenuActivity::class.java)
        intent.putExtra("menu", restaurant.menu)
        intent.putExtra("restaurant", restaurant)
        context?.startActivity(intent)
    }
}