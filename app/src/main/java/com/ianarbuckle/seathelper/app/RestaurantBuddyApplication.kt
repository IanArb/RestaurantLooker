package com.ianarbuckle.seathelper.app

import android.app.Application
import com.ianarbuckle.seathelper.app.builder.DaggerRestaurantBuddyAppComponent
import com.ianarbuckle.seathelper.app.builder.RestaurantBuddyAppComponent
import com.ianarbuckle.seathelper.app.builder.RestaurantBuddyModule
import com.ianarbuckle.seathelper.components.RestaurantsInitialiser
import com.ianarbuckle.seathelper.network.NetworkModule
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
class RestaurantBuddyApplication : Application() {

    @Inject lateinit var okHttpClient: OkHttpClient

    companion object {
        lateinit var component: RestaurantBuddyAppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerRestaurantBuddyAppComponent.builder()
                .restaurantBuddyModule(RestaurantBuddyModule(this))
                .networkModule(NetworkModule(this))
                .build()
        component.inject(this)

        RestaurantsInitialiser("https://restaurant-buddy-server.herokuapp.com", okHttpClient).init()
    }
}