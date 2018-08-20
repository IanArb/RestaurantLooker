package com.ianarbuckle.restaurants.home.router

import com.ianarbuckle.restaurants.home.model.Restaurants

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
interface RestaurantsRouter {
    fun onNavigateToBookingScreen(results: List<Restaurants>)
}