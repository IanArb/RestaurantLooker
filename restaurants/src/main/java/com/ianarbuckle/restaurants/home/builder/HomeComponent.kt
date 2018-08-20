package com.ianarbuckle.restaurants.home.builder

import com.ianarbuckle.restaurants.home.RestaurantsFragment
import com.ianarbuckle.restaurants.network.repository.RestaurantsRepository
import com.ianarbuckle.restaurants.network.builder.NetworkModule
import dagger.Component

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
@HomeScope
@Component(modules = [NetworkModule::class, HomeModule::class])
interface HomeComponent {
    fun inject(restaurantsFragment: RestaurantsFragment)
    fun restaurantsRepository(): RestaurantsRepository
}