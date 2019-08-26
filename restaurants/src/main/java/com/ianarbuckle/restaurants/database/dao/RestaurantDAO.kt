package com.ianarbuckle.restaurants.database.dao

import androidx.room.*
import com.ianarbuckle.restaurants.data.Restaurant
import com.ianarbuckle.restaurants.database.converters.RestaurantConverter

/**
 * Created by Ian Arbuckle on 2019-05-26.
 *
 */
@Dao
@TypeConverters(RestaurantConverter::class)
interface RestaurantDAO {

    @Query("SELECT * FROM restaurant")
    suspend fun getAllRestaurants(): MutableList<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurant: Restaurant?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(restaurants: MutableList<Restaurant>)
}