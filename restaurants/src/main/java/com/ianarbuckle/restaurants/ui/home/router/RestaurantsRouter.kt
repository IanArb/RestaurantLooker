package com.ianarbuckle.restaurants.ui.home.router

import com.ianarbuckle.models.restaurant.Restaurant

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
interface RestaurantsRouter {
    fun onNavigateToBookingScreen(restaurant: Restaurant?)
    fun navigateMenu(restaurant: Restaurant)
    fun navigateToBookingReservation(restaurant: Restaurant)
}