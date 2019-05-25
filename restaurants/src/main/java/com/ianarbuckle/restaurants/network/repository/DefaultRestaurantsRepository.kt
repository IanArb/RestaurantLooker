package com.ianarbuckle.restaurants.network.repository

import com.ianarbuckle.restaurants.data.Restaurant
import com.ianarbuckle.restaurants.network.manager.RestaurantsServiceManager
import kotlinx.coroutines.Deferred

/**
 * Created by Ian Arbuckle on 28/07/2018.
 *
 */
class DefaultRestaurantsRepository(private val service: RestaurantsServiceManager) : RestaurantsRepository {

    override fun fetchRestaurants(): Deferred<MutableList<Restaurant>> = service.getService().fetchRestaurants()
}