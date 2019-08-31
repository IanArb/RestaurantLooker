package com.ianarbuckle.booking.ui.bookings.core.view

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ianarbuckle.booking.R

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingView {
    fun getView(): View
}

class BookingViewImpl(context: Context) : BookingView, ConstraintLayout(context) {

    init {
        inflate(context, R.layout.booking_view, this)
    }

    override fun getView() = this
}