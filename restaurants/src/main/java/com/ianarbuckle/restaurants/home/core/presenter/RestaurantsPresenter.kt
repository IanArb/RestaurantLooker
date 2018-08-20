package com.ianarbuckle.restaurants.home.core.presenter

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
interface RestaurantsPresenter {
    fun onCreate()
    fun onDestroy()
    fun addLifecycleObserver()
}