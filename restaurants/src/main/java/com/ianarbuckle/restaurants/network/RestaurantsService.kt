package com.ianarbuckle.restaurants.network

import com.ianarbuckle.restaurants.home.model.Restaurant
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
interface RestaurantsService {

    @GET("/restaurants")
    fun fetchRestaurants(): Deferred<MutableList<Restaurant>>
}