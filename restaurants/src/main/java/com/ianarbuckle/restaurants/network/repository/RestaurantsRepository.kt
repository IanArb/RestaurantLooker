package com.ianarbuckle.restaurants.network.repository

import com.ianarbuckle.restaurants.data.Restaurant
import kotlinx.coroutines.Deferred

/**
 * Created by Ian Arbuckle on 28/07/2018.
 *
 */
interface RestaurantsRepository {
    fun fetchRestaurants(): Deferred<MutableList<Restaurant>>
}