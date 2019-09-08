package com.ianarbuckle.restaurants.ui.home.core.view.adapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ianarbuckle.restaurant.R
import com.ianarbuckle.restaurant.databinding.HomeRestaurantsCardItemBinding
import com.ianarbuckle.restaurants.data.Restaurant
import kotlinx.android.synthetic.main.home_restaurants_card_item.view.*

/**
 * Created by Ian Arbuckle on 30/07/2018.
 *
 */
class RestaurantsAdapter(private val restaurants: MutableList<Restaurant>)
    : RecyclerView.Adapter<RestaurantsAdapter.HomeViewHolder>() {

    var onMenuClickListener: ((Restaurant) -> Unit)? = null
    var onBookingClickListener: ((Restaurant) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: HomeRestaurantsCardItemBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.home_restaurants_card_item, parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = restaurants.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val items = restaurants[position]
        holder.binding.restaurant = items
        holder.onMenuClickListener(items)
        holder.onBookingClickListener(items)
    }

    inner class HomeViewHolder(val binding: HomeRestaurantsCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onMenuClickListener(restaurant: Restaurant) {
            binding.root.actionMenu.setOnClickListener {
                onMenuClickListener?.invoke(restaurant)
            }
        }

        fun onBookingClickListener(restaurant: Restaurant) {
            binding.root.actionBook.setOnClickListener {
                onBookingClickListener?.invoke(restaurant)
            }
        }
    }
}