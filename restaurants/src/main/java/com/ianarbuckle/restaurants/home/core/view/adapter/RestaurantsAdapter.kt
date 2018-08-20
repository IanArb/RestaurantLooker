package com.ianarbuckle.restaurants.home.core.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ianarbuckle.restaurants.R
import com.ianarbuckle.restaurants.databinding.HomeRestaurantsCardItemBinding
import com.ianarbuckle.restaurants.home.model.Restaurants
import com.ianarbuckle.restaurants.utils.provideImage
import kotlinx.android.synthetic.main.home_restaurants_card_item.view.*

/**
 * Created by Ian Arbuckle on 30/07/2018.
 *
 */
class HomeAdapter(private val restaurants: MutableList<Restaurants>)
    : RecyclerView.Adapter<HomeViewHolder>() {

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

        //FIXME Replace with url from API (dependency on API)
        val url = "https://images.pexels.com/photos/461198/pexels-photo-461198.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940"

        provideImage(holder.itemView.context, url, holder.itemView.bannerImageView)
    }
}

class HomeViewHolder(val binding: HomeRestaurantsCardItemBinding) : RecyclerView.ViewHolder(binding.root)