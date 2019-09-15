package com.ianarbuckle.database.database.builder

import android.content.Context
import com.ianarbuckle.database.client.DatabaseClient
import com.ianarbuckle.database.client.DatabaseClientImpl
import com.ianarbuckle.database.database.dao.RestaurantDAO
import com.ianarbuckle.database.database.RestaurantDatabase
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

/**
 * Created by Ian Arbuckle on 2019-05-26.
 *
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DatabaseScope

@Module
class DatabaseModule(private val context: Context) {

    @DatabaseScope
    @Provides
    fun provideRoomDatabase(): RestaurantDatabase = RestaurantDatabase.getDatabase(context)

    @DatabaseScope
    @Provides
    fun provideRestuarantDAO(restaurantDatabase: RestaurantDatabase): RestaurantDAO = restaurantDatabase.restaurantDao()

    @DatabaseScope
    @Provides
    fun provideDatabaseClient(restaurantDAO: RestaurantDAO): DatabaseClient = DatabaseClientImpl(restaurantDAO)
}

@DatabaseScope
@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    fun databaseClient(): DatabaseClient
}