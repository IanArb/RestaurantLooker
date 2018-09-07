package com.ianarbuckle.restaurants.home.core.interactor

import com.ianarbuckle.restaurants.home.model.Restaurant
import retrofit2.Response

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
interface RestaurantsInteractor {
    suspend fun fetchRestaurants(): MutableList<Restaurant>
}