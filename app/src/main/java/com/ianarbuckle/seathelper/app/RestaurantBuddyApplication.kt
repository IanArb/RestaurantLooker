package com.ianarbuckle.seathelper.app

import android.app.Application
import com.ianarbuckle.seathelper.app.builder.DaggerAppComponent
import com.ianarbuckle.seathelper.app.builder.AppComponent
import com.ianarbuckle.seathelper.app.builder.AppModule
import com.ianarbuckle.seathelper.components.BookingInitialiser
import com.ianarbuckle.seathelper.components.RestaurantsInitialiser
import com.ianarbuckle.seathelper.components.TablesMapInitializer
import com.ianarbuckle.seathelper.network.NetworkModule
import net.danlew.android.joda.JodaTimeAndroid
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
class RestaurantBuddyApplication : Application() {

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @field:[Inject Named("baseUrl")]
    lateinit var baseUrl: String

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

        RestaurantsInitialiser(baseUrl, okHttpClient).init()
        TablesMapInitializer(baseUrl, okHttpClient).init()
        BookingInitialiser(baseUrl, okHttpClient).init()
    }

}