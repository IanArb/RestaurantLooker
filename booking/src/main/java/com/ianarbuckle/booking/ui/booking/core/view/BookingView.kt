package com.ianarbuckle.booking.ui.booking.core.view

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ianarbuckle.booking.R
import com.ianarbuckle.core.extensions.provideImage
import com.ianarbuckle.models.booking.Booking
import kotlinx.android.synthetic.main.booking_view.view.*

interface BookingView {
    fun getView(): View
    fun showBooking(booking: Booking)
    fun showEmptyState()
    fun showLoading()
    fun hideLoading()
    fun tryAgainClickListener(clickListener: () -> Unit)
    fun cancelClickListener(clickListener: () -> Unit)
    fun viewBookingsClickListener(clickListener: () -> Unit)
}

class BookingViewImpl(context: Context) : BookingView, ConstraintLayout(context) {

    override fun getView(): View = this

    init {
        inflate(context, R.layout.booking_view, this)
    }

    override fun showBooking(booking: Booking) {
        upcomingText.visibility = View.VISIBLE
        bookingCard.visibility = View.VISIBLE
        actionViewBookings.visibility = View.VISIBLE
        booking.apply {
            restaurantDetails?.imageUrl?.let { bookingImage.provideImage(context, it) }
            bookingRestaurantName.text = restaurantDetails?.name
            bookingAddress.text = restaurantDetails?.address
            bookingArrivalTime.text = owner?.arrivalTime
            bookingDate.text = owner?.bookingDate
        }
    }

    override fun showEmptyState() {
        upcomingText.visibility = GONE
        bookingCard.visibility = GONE
        actionViewBookings.visibility = GONE

        emptyStateTitle.visibility = VISIBLE
        errorMessage.visibility = VISIBLE
        tryAgainButton.visibility = VISIBLE
    }

    override fun tryAgainClickListener(clickListener: () -> Unit) {
        tryAgainButton.setOnClickListener {
            clickListener()
        }
    }

    override fun cancelClickListener(clickListener: () -> Unit) {
        actionCancel.setOnClickListener {
            clickListener()
        }
    }

    override fun viewBookingsClickListener(clickListener: () -> Unit) {
        actionViewBookings.setOnClickListener {
            clickListener()
        }
    }

    override fun showLoading() {
        bookingCard.visibility = GONE
        upcomingText.visibility = GONE
        actionViewBookings.visibility = GONE

        progressBar.visibility = VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = GONE
    }


}