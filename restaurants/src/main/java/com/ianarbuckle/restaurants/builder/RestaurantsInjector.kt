package com.ianarbuckle.restaurants.builder

import com.ianarbuckle.restaurants.ui.home.RestaurantsFragment
import com.ianarbuckle.restaurants.ui.menu.MenuActivity

/**
 * Created by Ian Arbuckle on 15/07/2018.
 *
 */
interface RestaurantsInjector {
    fun inject(fragment: RestaurantsFragment)
    fun inject(activity: MenuActivity)
}