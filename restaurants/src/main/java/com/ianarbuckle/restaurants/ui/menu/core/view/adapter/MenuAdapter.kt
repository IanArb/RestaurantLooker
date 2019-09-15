package com.ianarbuckle.restaurants.ui.menu.core.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ianarbuckle.models.restaurant.Dish
import com.ianarbuckle.restaurant.R
import com.ianarbuckle.restaurant.databinding.MenuItemBinding

/**
 * Created by Ian Arbuckle on 2019-05-11.
 *
 */
class MenuAdapter(private val results: List<Dish>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MenuItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.menu_item, parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val items = results[position]
        holder.binding.dish = items
    }

    inner class MenuViewHolder(val binding: MenuItemBinding): RecyclerView.ViewHolder(binding.root)
}