package com.ianarbuckle.booking.ui.confirmation.view

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ianarbuckle.booking.R
import com.ianarbuckle.core.extensions.provideImage
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
            title.text = context.getString(R.string.reservation_placeholder, owner?.name)
            bookingImage.provideImage(context, restaurantDetails?.imageUrl!! )
            bookingRestaurantName.text = booking.restaurantDetails?.name
            bookingAddress.text = booking.restaurantDetails?.address
            bookingArrivalTime.text = owner?.arrivalTime
            bookingDate.text = owner?.bookingDate
        }
    }

    override fun onBookingsClickListener(clickListener: () -> Unit) {
        finishButton.setOnClickListener { clickListener() }
    }
}
