package com.ianarbuckle.booking.ui.bookings.core.router

import com.ianarbuckle.booking.ui.bookings.BookingsActivity

interface BookingsRouter {
    fun navigateBack()
}

class BookingsRouterImpl(private val activity: BookingsActivity) : BookingsRouter {

    override fun navigateBack() {
        activity.finish()
    }
}