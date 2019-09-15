package com.ianarbuckle.booking.ui.confirmation.view

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ianarbuckle.booking.R
import com.ianarbuckle.models.booking.Booking
import kotlinx.android.synthetic.main.confirmation_view.view.*

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */

interface ConfirmationView {
    fun getView(): View
    fun showBooking(booking: Booking)
    fun onBookingsClickListener(clickListener: () -> Unit)
}

class ConfirmationViewImpl(context: Context) : ConfirmationView, ConstraintLayout(context) {

    init {
        inflate(context, R.layout.confirmation_view, this)
    }

    override fun getView() = this

    override fun showBooking(booking: Booking) {
        booking.apply {
            customerNameText.text = owner?.name
            restaurantNameText.text = restaurantName
            bookingDateText.text = owner?.bookingDate
            arrivalTimeText.text = owner?.arrivalTime
            tableNumberText.text = table?.tableNumber
        }
    }

    override fun onBookingsClickListener(clickListener: () -> Unit) {
        finishButton.setOnClickListener { clickListener() }
    }
}
