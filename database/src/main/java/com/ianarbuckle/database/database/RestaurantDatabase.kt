package com.ianarbuckle.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ianarbuckle.database.database.converters.DishConverter
import com.ianarbuckle.database.database.converters.LocalDateTimeConverter
import com.ianarbuckle.database.database.converters.LocationConverter
import com.ianarbuckle.database.database.dao.RestaurantDAO
import com.ianarbuckle.models.restaurant.Restaurant

/**
 * Created by Ian Arbuckle on 2019-05-26.
 *
 */
@Database(entities = [Restaurant::class], version = 1)
@TypeConverters(LocationConverter::class, DishConverter::class, LocalDateTimeConverter::class)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDAO

    companion object {
        @Volatile
        private var INSTANCE: RestaurantDatabase? = null

        fun getDatabase(context: Context): RestaurantDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RestaurantDatabase::class.java,
                        "restaurant_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}