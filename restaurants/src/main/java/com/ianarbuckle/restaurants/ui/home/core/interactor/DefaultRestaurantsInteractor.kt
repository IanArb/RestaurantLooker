package com.ianarbuckle.restaurants.ui.home.core.interactor

import com.ianarbuckle.restaurants.ui.home.core.repository.RestaurantsRepository
import com.ianarbuckle.restaurants.data.Restaurant
/**
 * Created by Ian Arbuckle on 20/07/2018.
 *
 */
class DefaultRestaurantsInteractor(private val repository: RestaurantsRepository) : RestaurantsInteractor {

    override suspend fun fetchRestaurants(): MutableList<Restaurant> = repository.fetchRestaurants()

    override suspend fun getSavedRestaurants(): MutableList<Restaurant> = repository.getAllRestaurants()

    override suspend fun saveRestaurants(restaurants: MutableList<Restaurant>) {
        repository.insertRestaurants(restaurants).takeIf { !restaurants.isNullOrEmpty() }
    }
}