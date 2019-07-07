package com.ianarbuckle.restaurants.ui.home.core.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.ianarbuckle.restaurants.data.Restaurant
import kotlinx.coroutines.Deferred
import org.joda.time.LocalDateTime

/**
 * Created by Ian Arbuckle on 28/07/2018.
 *
 */
interface RestaurantsRepository {
    suspend fun fetchRestaurants(): MutableList<Restaurant>
    suspend fun getAllRestaurants(): MutableList<Restaurant>
    @WorkerThread
    suspend fun insertRestaurants(restaurants: MutableList<Restaurant>)
}