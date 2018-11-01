package com.ianarbuckle.restaurants.home.core.view.adapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ianarbuckle.restaurants.R
import com.ianarbuckle.restaurants.databinding.HomeRestaurantsCardItemBinding
import com.ianarbuckle.restaurants.home.model.Restaurants
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.rx2.awaitFirst

/**
 * Created by Ian Arbuckle on 30/07/2018.
 *
 */
class HomeAdapter(private val restaurants: MutableList<Restaurants>)
    : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: HomeRestaurantsCardItemBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.home_restaurants_card_item, parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if(restaurants.isEmpty()) {
            return 0
        }
        return restaurants.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val items = restaurants[position]
        holder.binding.restaurants = items
    }

    inner class HomeViewHolder(val binding: HomeRestaurantsCardItemBinding) : RecyclerView.ViewHolder(binding.root)
}