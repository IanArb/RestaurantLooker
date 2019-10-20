package com.ianarbuckle.booking.ui.bookings.core.view

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.ianarbuckle.booking.R
import com.ianarbuckle.booking.ui.bookings.core.view.adapter.BookingsAdapter
import com.ianarbuckle.core.extensions.getColorFromAttr
import com.ianarbuckle.models.booking.Booking
import kotlinx.android.synthetic.main.bookings_view.view.*

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingsView {
    fun getView(): View
    fun showBookings(bookings: MutableList<Booking>)
    fun showLoading()
    fun hideLoading()
    fun showErrorMessage()
    fun tryAgainClickListener(clickListener: () -> Unit)
}

class BookingsViewImpl(context: Context) : BookingsView, ConstraintLayout(context) {

    init {
        inflate(context, R.layout.bookings_view, this)
    }

    override fun getView(): View {
        return this
    }

    override fun showBookings(bookings: MutableList<Booking>) {
        recyclerViewBookings.apply {
            visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = BookingsAdapter(bookings)
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showErrorMessage() {
        errorMessage.visibility = View.VISIBLE
        errorImageView.visibility = View.VISIBLE
        recyclerViewBookings.visibility = View.GONE
    }

    override fun tryAgainClickListener(clickListener: () -> Unit) {
        tryAgainButton.setOnClickListener {
            clickListener()
        }
    }
}