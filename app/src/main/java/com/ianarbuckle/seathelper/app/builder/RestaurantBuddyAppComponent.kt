package com.ianarbuckle.seathelper.app.builder

import com.ianarbuckle.seathelper.app.RestaurantBuddyApplication
import com.ianarbuckle.seathelper.network.NetworkModule
import dagger.Component
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
@RestaurantBuddyAppScope
@Component(modules = [RestaurantBuddyModule::class, NetworkModule::class])
interface RestaurantBuddyAppComponent {
    fun inject(restaurantBuddyApplication: RestaurantBuddyApplication)
    fun okHttpClient(): OkHttpClient
    fun interceptor(): Interceptor
    fun cache(): Cache
}