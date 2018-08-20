package com.ianarbuckle.restaurants.home.core.interactor

import com.ianarbuckle.restaurants.network.repository.RestaurantsRepository
import com.ianarbuckle.restaurants.home.model.Restaurant
import retrofit2.Response

/**
 * Created by Ian Arbuckle on 20/07/2018.
 *
 */
class DefaultRestaurantsInteractor(private val repository: RestaurantsRepository) : RestaurantsInteractor {

    override suspend fun fetchRestaurants(): MutableList<Restaurant> = repository.fetchRestaurants().await()
}