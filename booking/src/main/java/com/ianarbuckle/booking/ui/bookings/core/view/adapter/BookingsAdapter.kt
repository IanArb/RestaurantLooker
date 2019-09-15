package com.ianarbuckle.booking.ui.bookings.core.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ianarbuckle.booking.R
import com.ianarbuckle.models.booking.Booking

/**
 * Created by Ian Arbuckle on 2019-09-13.
 *
 */
class BookingsAdapter(private val bookings: MutableList<Booking>) : RecyclerView.Adapter<BookingsAdapter.BookingsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.bookings_item, parent, false)
        return BookingsViewHolder(layout)
    }

    override fun getItemCount(): Int = bookings.size

    override fun onBindViewHolder(holder: BookingsViewHolder, position: Int) {
        val items = bookings[position]
        items.owner?.arrivalTime?.let { holder.setArrivalTimeText(it) }
        items.owner?.bookingDate?.let { holder.setBookingDateText(it) }
        items.restaurantName?.let { holder.setRestaurantName(it) }
    }

    inner class BookingsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val restaurantNameText = view.findViewById<TextView>(R.id.restaurantName)
        private val bookingDateText = view.findViewById<TextView>(R.id.bookingDate)
        private val arrivalTimeText = view.findViewById<TextView>(R.id.arrivalTimeText)

        fun setRestaurantName(name: String) {
            restaurantNameText.text = name
        }

        fun setBookingDateText(name: String) {
            bookingDateText.text = name
        }

        fun setArrivalTimeText(name: String) {
            arrivalTimeText.text = name
        }
    }
}