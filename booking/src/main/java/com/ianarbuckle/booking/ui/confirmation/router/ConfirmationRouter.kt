package com.ianarbuckle.booking.ui.confirmation.router

import android.app.Activity
import com.ianarbuckle.booking.ui.confirmation.ConfirmationActivity

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */

interface ConfirmationRouter {
    fun navigateToBookings()
}

class ConfirmationRouterImpl(private val activity: ConfirmationActivity, private val bookingsCallback: (ConfirmationActivity) -> Unit) : ConfirmationRouter {

    override fun navigateToBookings() {
        bookingsCallback.invoke(activity)
    }
}
