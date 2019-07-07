package com.ianarbuckle.restaurants.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ianarbuckle.restaurants.data.Restaurant
import com.ianarbuckle.restaurants.db.converters.DishConverter
import com.ianarbuckle.restaurants.db.converters.LocalDateTimeConverter
import com.ianarbuckle.restaurants.db.converters.LocationConverter
import com.ianarbuckle.restaurants.db.dao.RestaurantDAO

/**
 * Created by Ian Arbuckle on 2019-05-26.
 *
 */
@Database(entities = [Restaurant::class], version = 1)
@TypeConverters(LocationConverter::class, DishConverter::class, LocalDateTimeConverter::class)
abstract class RestaurantRoomDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDAO

    companion object {
        @Volatile
        private var INSTANCE: RestaurantRoomDatabase? = null

        fun getDatabase(context: Context): RestaurantRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RestaurantRoomDatabase::class.java,
                        "restaurant_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}