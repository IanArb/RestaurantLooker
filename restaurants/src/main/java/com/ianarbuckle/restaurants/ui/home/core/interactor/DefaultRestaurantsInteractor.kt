package com.ianarbuckle.restaurants.ui.home.core.interactor

import android.content.Context
import androidx.lifecycle.Transformations
import com.ianarbuckle.restaurants.ui.home.core.repository.RestaurantsRepository
import com.ianarbuckle.restaurants.data.Restaurant
import com.ianarbuckle.restaurants.utils.isConnected
import org.joda.time.LocalDateTime

/**
 * Created by Ian Arbuckle on 20/07/2018.
 *
 */
class DefaultRestaurantsInteractor(private val context: Context, private val repository: RestaurantsRepository) : RestaurantsInteractor {

    override suspend fun fetchRestaurants(): MutableList<Restaurant> = repository.fetchRestaurants()

    override fun isConnected(): Boolean = context.isConnected()

    override suspend fun getSavedRestaurants(): MutableList<Restaurant> = repository.getAllRestaurants()

    override suspend fun saveRestaurants(restaurants: MutableList<Restaurant>) {
        repository.insertRestaurants(restaurants).takeIf { !restaurants.isNullOrEmpty() }
    }

    override suspend fun isContentSame(restaurants: MutableList<Restaurant>): Boolean {
        return restaurants.takeIf { !it.isNullOrEmpty() } == repository.getAllRestaurants()
    }
}