package com.ianarbuckle.restaurants.ui.home.core.interactor

import com.ianarbuckle.models.restaurant.Restaurant

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
interface RestaurantsInteractor {
    suspend fun fetchRestaurants(): MutableList<Restaurant>
    suspend fun getSavedRestaurants(): MutableList<Restaurant>
    suspend fun saveRestaurants(restaurants: MutableList<Restaurant>)
}