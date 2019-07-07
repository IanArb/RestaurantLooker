package com.ianarbuckle.seathelper.app

import android.app.Application
import com.ianarbuckle.seathelper.app.builder.DaggerAppComponent
import com.ianarbuckle.seathelper.app.builder.AppComponent
import com.ianarbuckle.seathelper.app.builder.AppModule
import com.ianarbuckle.seathelper.components.RestaurantsInitialiser
import com.ianarbuckle.seathelper.network.NetworkModule
import net.danlew.android.joda.JodaTimeAndroid
import okhttp3.OkHttpClient
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
class RestaurantBuddyApplication : Application() {

    @Inject lateinit var okHttpClient: OkHttpClient

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(this))
                .build()
        component.inject(this)

        JodaTimeAndroid.init(this)

        RestaurantsInitialiser("https://restaurant-buddy-server.herokuapp.com", okHttpClient).init()
    }

}