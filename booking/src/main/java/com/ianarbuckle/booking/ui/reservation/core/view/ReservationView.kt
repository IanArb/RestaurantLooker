package com.ianarbuckle.booking.ui.reservation.core.view

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface ReservationView {
    fun getView(): View
}

class ReservationViewImpl(context: Context) : ReservationView, ConstraintLayout(context) {

    override fun getView(): View = this
}