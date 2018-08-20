package com.ianarbuckle.restaurants.network.repository

import com.ianarbuckle.restaurants.home.model.Restaurant
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response

/**
 * Created by Ian Arbuckle on 28/07/2018.
 *
 */
interface RestaurantsRepository {
    fun fetchRestaurants(): Deferred<MutableList<Restaurant>>
}