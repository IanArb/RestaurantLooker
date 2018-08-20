package com.ianarbuckle.restaurants.builder

import android.arch.lifecycle.LifecycleOwner
import com.ianarbuckle.restaurants.home.RestaurantsFragment

/**
 * Created by Ian Arbuckle on 15/07/2018.
 *
 */
interface RestaurantsInjector {
    fun inject(fragment: RestaurantsFragment, lifecycleOwner: LifecycleOwner)
}