package com.ianarbuckle.restaurants.ui.home.core.repository

import androidx.annotation.WorkerThread
import com.ianarbuckle.models.restaurant.Restaurant

/**
 * Created by Ian Arbuckle on 28/07/2018.
 *
 */
interface RestaurantsRepository {
    suspend fun fetchRestaurants(): MutableList<Restaurant>
    suspend fun getAllRestaurants(): MutableList<Restaurant>
    @WorkerThread
    suspend fun insertRestaurants(restaurants: MutableList<Restaurant>)
}