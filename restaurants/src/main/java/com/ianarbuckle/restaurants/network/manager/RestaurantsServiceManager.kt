package com.ianarbuckle.restaurants.network.manager

import com.ianarbuckle.restaurants.network.RestaurantsService

/**
 * Created by Ian Arbuckle on 20/08/2018.
 *
 */
interface RestaurantsServiceManager {
    fun getService(): RestaurantsService
}