package com.ianarbuckle.seathelper.app.builder

import com.ianarbuckle.booking.BookingNavigator
import com.ianarbuckle.navigation.booking.BookingCoordinator
import com.ianarbuckle.navigation.restaurants.RestaurantsCoordinator
import com.ianarbuckle.restaurants.RestaurantsNavigator
import dagger.Module
import dagger.Provides

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
@Module
class NavigationModule {

    @AppScope
    @Provides
    fun provideRestaurantsNavigator(): RestaurantsNavigator = RestaurantsCoordinator()

    @AppScope
    @Provides
    fun provideBookingNavigator(): BookingNavigator = BookingCoordinator()
}