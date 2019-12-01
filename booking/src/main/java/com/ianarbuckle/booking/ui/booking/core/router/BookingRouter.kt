package com.ianarbuckle.booking.ui.booking.core.router

import android.content.Context
import android.content.Intent
import com.ianarbuckle.booking.ui.bookings.BookingsActivity

interface BookingRouter {
    fun navigateToViewAllBookings()
    fun navigateToRestaurants()
}

class BookingRouterImpl(private val context: Context): BookingRouter {

    override fun navigateToViewAllBookings() {
        val intent = Intent(context, BookingsActivity::class.java)
        context.startActivity(intent)
    }

    override fun navigateToRestaurants() {

    }
}