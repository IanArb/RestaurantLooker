package com.ianarbuckle.restaurants.ui.home.core.interactor

import com.ianarbuckle.restaurants.data.Restaurant

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
interface RestaurantsInteractor {
    suspend fun fetchRestaurants(): MutableList<Restaurant>
}