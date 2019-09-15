package com.ianarbuckle.restaurants.ui.home.core.view

import android.view.View
import com.ianarbuckle.models.restaurant.Restaurant

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
interface RestaurantsView {
    fun getView(): View
    fun showRestaurants(restaurants: MutableList<Restaurant>)
    fun showEmptyState()
    fun showErrorState()
    fun showLoading()
    fun hideLoading()
    fun executeTryAgainClick(clickListener: () -> Unit)
    fun onMenuClickListener(menuClickListener: (Restaurant) -> Unit)
    fun onBookingClickListener(bookClickListener: (Restaurant) -> Unit)
}