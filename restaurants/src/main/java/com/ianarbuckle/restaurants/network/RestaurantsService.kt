package com.ianarbuckle.restaurants.network

import com.ianarbuckle.models.restaurant.Restaurant
import retrofit2.http.GET

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
interface RestaurantsService {

    @GET("/restaurants")
    suspend fun fetchRestaurants(): MutableList<Restaurant>
}