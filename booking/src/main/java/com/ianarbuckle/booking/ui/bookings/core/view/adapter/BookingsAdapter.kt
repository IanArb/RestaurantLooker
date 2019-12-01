package com.ianarbuckle.booking.ui.bookings.core.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ianarbuckle.booking.R
import com.ianarbuckle.booking.databinding.BookingsItemBinding
import com.ianarbuckle.models.booking.Booking

/**
 * Created by Ian Arbuckle on 2019-09-13.
 *
 */
class BookingsAdapter(private val bookings: List<Booking>)
    : RecyclerView.Adapter<BookingsAdapter.BookingsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: BookingsItemBinding
                = DataBindingUtil.inflate(inflater, R.layout.bookings_item, parent, false)
        return BookingsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return bookings.size
    }

    override fun onBindViewHolder(holder: BookingsViewHolder, position: Int) {
        val items = bookings[position]
        holder.binding.booking = items
    }

    inner class BookingsViewHolder(val binding: BookingsItemBinding) : RecyclerView.ViewHolder(binding.root)
}