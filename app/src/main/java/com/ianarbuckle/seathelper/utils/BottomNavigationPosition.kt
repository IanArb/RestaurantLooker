package com.ianarbuckle.seathelper.utils

import androidx.fragment.app.Fragment
import com.ianarbuckle.booking.ui.booking.BookingFragment
import com.ianarbuckle.booking.ui.bookings.BookingsActivity
import com.ianarbuckle.restaurants.ui.home.RestaurantsFragment
import com.ianarbuckle.seathelper.R
import com.ianarbuckle.seathelper.home.fragment.MessagesFragment
import com.ianarbuckle.seathelper.home.fragment.ProfileFragment

/**
 * Created by Ian Arbuckle on 30/06/2018.
 *
 */
enum class BottomNavigationPosition(val position: Int, val id: Int) {
    HOME(0, R.id.nav_menu_home),
    BOOKINGS(1, R.id.nav_menu_booking),
    PROFILE(2, R.id.nav_menu_profile),
    MESSAGES(3, R.id.nav_menu_messages)
}

 fun findNavigationById(id: Int): BottomNavigationPosition = when(id) {
     BottomNavigationPosition.HOME.id -> BottomNavigationPosition.HOME
     BottomNavigationPosition.BOOKINGS.id -> BottomNavigationPosition.BOOKINGS
     BottomNavigationPosition.MESSAGES.id -> BottomNavigationPosition.MESSAGES
     BottomNavigationPosition.PROFILE.id -> BottomNavigationPosition.PROFILE
     else -> BottomNavigationPosition.HOME
 }

 fun BottomNavigationPosition.createFragment(): Fragment = when(this) {
     BottomNavigationPosition.HOME -> RestaurantsFragment.newInstance()
     BottomNavigationPosition.BOOKINGS -> BookingFragment.newInstance()
     BottomNavigationPosition.PROFILE -> ProfileFragment.newInstance()
     BottomNavigationPosition.MESSAGES -> MessagesFragment.newInstance()
 }

 fun BottomNavigationPosition.getTag(): String = when(this) {
     BottomNavigationPosition.HOME -> RestaurantsFragment.TAG
     BottomNavigationPosition.BOOKINGS -> BookingFragment.TAG
     BottomNavigationPosition.PROFILE -> ProfileFragment.TAG
     BottomNavigationPosition.MESSAGES -> MessagesFragment.TAG
 }