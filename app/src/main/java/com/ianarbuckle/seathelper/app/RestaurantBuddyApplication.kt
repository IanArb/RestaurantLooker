package com.ianarbuckle.seathelper.app

import android.app.Application
import com.ianarbuckle.booking.BookingNavigator
import com.ianarbuckle.core.utils.DeviceUuidFactory
import com.ianarbuckle.database.client.DatabaseClient
import com.ianarbuckle.restaurants.RestaurantsNavigator
import com.ianarbuckle.seathelper.app.builder.*
import com.ianarbuckle.seathelper.components.BookingInitialiser
import com.ianarbuckle.seathelper.components.RestaurantsInitialiser
import com.ianarbuckle.seathelper.network.NetworkModule
import com.jakewharton.threetenabp.AndroidThreeTen
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

    @Inject
    lateinit var databaseClient: DatabaseClient

    @Inject
    lateinit var uuidFactory: DeviceUuidFactory

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
                .databaseModule(DatabaseModule(this))
                .build()
        component.inject(this)

        country = Locale.getDefault().country

        AndroidThreeTen.init(this)
        //TODO Remove joda time
        JodaTimeAndroid.init(this)

        RestaurantsInitialiser(baseUrl, okHttpClient, restaurantsNavigator, databaseClient).init()
        BookingInitialiser(baseUrl, okHttpClient, bookingNavigator, country, databaseClient, uuidFactory).init()
    }
}