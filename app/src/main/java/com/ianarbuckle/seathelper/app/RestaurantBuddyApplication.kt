package com.ianarbuckle.seathelper.app

import android.app.Application
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import com.ianarbuckle.booking.BookingNavigator
import com.ianarbuckle.restaurants.RestaurantsNavigator
import com.ianarbuckle.seathelper.app.builder.DaggerAppComponent
import com.ianarbuckle.seathelper.app.builder.AppComponent
import com.ianarbuckle.seathelper.app.builder.AppModule
import com.ianarbuckle.seathelper.app.builder.NavigationModule
import com.ianarbuckle.seathelper.components.BookingInitialiser
import com.ianarbuckle.seathelper.components.RestaurantsInitialiser
import com.ianarbuckle.seathelper.components.TablesMapInitializer
import com.ianarbuckle.seathelper.network.NetworkModule
import net.danlew.android.joda.JodaTimeAndroid
import okhttp3.OkHttpClient
import java.util.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
class RestaurantBuddyApplication : Application() {

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var restaurantsNavigator: RestaurantsNavigator

    @Inject
    lateinit var bookingNavigator: BookingNavigator

    @field:[Inject Named("baseUrl")]
    lateinit var baseUrl: String

    companion object {
        lateinit var component: AppComponent
    }

    lateinit var country: String

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(this))
                .navigationModule(NavigationModule())
                .build()
        component.inject(this)

        country = Locale.getDefault().country

        JodaTimeAndroid.init(this)

        RestaurantsInitialiser(baseUrl, okHttpClient, restaurantsNavigator).init()
        BookingInitialiser(baseUrl, okHttpClient, bookingNavigator, country).init()
        TablesMapInitializer(baseUrl, okHttpClient).init()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        country = newConfig.locales[0].country
    }
}