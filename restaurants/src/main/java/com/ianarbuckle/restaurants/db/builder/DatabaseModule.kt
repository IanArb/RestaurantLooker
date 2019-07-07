package com.ianarbuckle.restaurants.db.builder

import android.content.Context
import com.ianarbuckle.restaurants.db.RestaurantRoomDatabase
import com.ianarbuckle.restaurants.db.dao.RestaurantDAO
import com.ianarbuckle.restaurants.ui.home.builder.HomeScope
import dagger.Module
import dagger.Provides

/**
 * Created by Ian Arbuckle on 2019-05-26.
 *
 */
@Module
class DatabaseModule {

    @HomeScope
    @Provides
    fun provideRoomDatabase(context: Context): RestaurantRoomDatabase = RestaurantRoomDatabase.getDatabase(context)

    @HomeScope
    @Provides
    fun provideRestuarantDAO(database: RestaurantRoomDatabase): RestaurantDAO = database.restaurantDao()
}