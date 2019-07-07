package com.ianarbuckle.restaurants.ui.home.builder

import com.ianarbuckle.restaurants.db.builder.DatabaseModule
import com.ianarbuckle.restaurants.ui.home.RestaurantsFragment
import com.ianarbuckle.restaurants.network.builder.NetworkModule
import dagger.Component

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
@HomeScope
@Component(modules = [NetworkModule::class, HomeModule::class, DatabaseModule::class])
interface HomeComponent {
    fun inject(restaurantsFragment: RestaurantsFragment)
}