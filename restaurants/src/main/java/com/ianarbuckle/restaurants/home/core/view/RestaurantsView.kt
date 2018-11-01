package com.ianarbuckle.restaurants.home.core.view

import android.view.View
import com.ianarbuckle.restaurants.home.model.Restaurant
import com.ianarbuckle.restaurants.home.model.Restaurants
import io.reactivex.Observable
import kotlinx.coroutines.experimental.channels.ReceiveChannel

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
interface RestaurantsView {
    fun getView(): View
    fun showRestaurants(restaurants: MutableList<Restaurants>)
    fun showEmptyState()
    fun showErrorState()
    fun showLoading()
    fun hideLoading()
    fun observeOnPullToRefresh(): Observable<Unit>
    fun terminatePullToRefresh()
    fun observeOnTryAgainClick(): Observable<Unit>
}