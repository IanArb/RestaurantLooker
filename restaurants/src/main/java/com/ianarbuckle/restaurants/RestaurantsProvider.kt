package com.ianarbuckle.restaurants

import com.ianarbuckle.restaurants.builder.RestaurantsInjector

/**
 * Created by Ian Arbuckle on 19/07/2018.
 *
 */
class RestaurantsProvider {

    companion object {
        private lateinit var injector: RestaurantsInjector

        fun init(injector: RestaurantsInjector): RestaurantsProvider {
            RestaurantsProvider.injector = injector
            return RestaurantsProvider()
        }

        fun get(): RestaurantsInjector {
            return injector
        }
    }


}