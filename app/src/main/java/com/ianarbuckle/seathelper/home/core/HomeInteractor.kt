package com.ianarbuckle.seathelper.home.core

import android.app.Activity

/**
 * Created by Ian Arbuckle on 2019-09-13.
 *
 */
interface HomeInteractor {
    fun getConfirmationCallbackPosition(): Int
}

class HomeInteractorImpl(private val activity: Activity) : HomeInteractor {

    override fun getConfirmationCallbackPosition(): Int {
        return activity.intent.getIntExtra("bookingPosition", 0)
    }
}