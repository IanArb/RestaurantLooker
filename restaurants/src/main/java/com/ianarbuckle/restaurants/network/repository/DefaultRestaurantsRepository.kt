package com.ianarbuckle.restaurants.network.repository

import com.ianarbuckle.restaurants.home.model.Restaurant
import com.ianarbuckle.restaurants.network.manager.RestaurantsServiceManager
import kotlinx.coroutines.experimental.Deferred

/**
 * Created by Ian Arbuckle on 28/07/2018.
 *
 */
class DefaultRestaurantsRepository(private val serviceDefault: RestaurantsServiceManager) : RestaurantsRepository {

    override fun fetchRestaurants(): Deferred<MutableList<Restaurant>> = serviceDefault.getService().fetchRestaurants()
}