package com.ianarbuckle.database.client

import com.ianarbuckle.database.database.dao.RestaurantDAO

/**
 * Created by Ian Arbuckle on 2019-09-10.
 *
 */
interface DatabaseClient {
    fun getRestaurantsDAO(): RestaurantDAO
}

class DatabaseClientImpl(private val restaurantDAO: RestaurantDAO) : DatabaseClient {

    override fun getRestaurantsDAO(): RestaurantDAO = restaurantDAO
}