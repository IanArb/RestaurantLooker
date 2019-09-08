package com.ianarbuckle.booking.ui.phonePrefix.core.router

import android.app.Activity
import android.content.Intent
import com.ianarbuckle.booking.ui.reservation.ReservationActivity

/**
 * Created by Ian Arbuckle on 2019-09-03.
 *
 */
interface PhonePrefixRouter {
    fun navigateBack()
    fun navigateBackWithPrefix(prefix: String)
}

class PhonePrefixRouterImpl(private val activity: Activity) : PhonePrefixRouter {

    override fun navigateBack() {
        activity.finish()
    }

    override fun navigateBackWithPrefix(prefix: String) {
        val intent = Intent(activity, ReservationActivity::class.java)
        intent.putExtra("prefix", prefix)
        activity.startActivity(intent)
    }
}