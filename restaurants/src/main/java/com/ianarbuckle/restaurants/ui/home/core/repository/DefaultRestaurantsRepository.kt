package com.ianarbuckle.restaurants.ui.home.core.repository

import androidx.annotation.WorkerThread
import com.ianarbuckle.restaurants.data.Restaurant
import com.ianarbuckle.restaurants.db.dao.RestaurantDAO
import com.ianarbuckle.restaurants.network.manager.RestaurantsServiceManager

/**
 * Created by Ian Arbuckle on 28/07/2018.
 *
 */
class DefaultRestaurantsRepository(private val service: RestaurantsServiceManager, private val dao: RestaurantDAO) : RestaurantsRepository {

    override suspend fun fetchRestaurants(): MutableList<Restaurant> = service.getService().fetchRestaurants()

    override suspend fun getAllRestaurants(): MutableList<Restaurant> = dao.getAllRestaurants()

    @WorkerThread
    override suspend fun insertRestaurants(restaurants: MutableList<Restaurant>) = dao.insertAll(restaurants)
}