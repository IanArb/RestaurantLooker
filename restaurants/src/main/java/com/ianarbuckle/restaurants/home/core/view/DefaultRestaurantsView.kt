package com.ianarbuckle.restaurants.home.core.view

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.ianarbuckle.restaurants.R
import com.ianarbuckle.restaurants.home.core.view.adapter.HomeAdapter
import com.ianarbuckle.restaurants.home.model.Restaurants
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.home_restaurants_view.view.*

/**
 * Created by Ian Arbuckle on 20/07/2018.
 *
 */
class DefaultRestaurantsView(context: Context?) : RestaurantsView, LinearLayout(context) {

    init {
        inflate(context, R.layout.home_restaurants_view, this)
        toolbar.apply {
            title = resources.getString(R.string.title_restaurants)
            setTitleTextColor(ContextCompat.getColor(this.context, R.color.colorWhite))
        }
    }

    override fun getView(): View {
        return this
    }

    override fun showRestaurants(restaurants: MutableList<Restaurants>?) {
        recyclerView.apply {
            visibility = View.VISIBLE
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = restaurants?.let { HomeAdapter(it) }
        }
    }

    override fun showEmptyState() {
        recyclerView.visibility = View.GONE
        errorMessage.visibility = View.VISIBLE
        errorImageView.visibility = View.VISIBLE
        tryAgainButton.visibility = View.VISIBLE
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun terminatePullToRefresh() {
        if(swipeRefresh.isRefreshing) {
            swipeRefresh.isEnabled = false
        }
    }

    override fun showPullToRefresh() {
        swipeRefresh.post {
            if(!swipeRefresh.isRefreshing) {
                swipeRefresh.isEnabled = true
            }
        }
    }

    override fun observeOnTryAgainClick(): Observable<Any> = RxView.clicks(this)

    override fun observeOnPullToRefresh(): Observable<Any> = RxSwipeRefreshLayout.refreshes(swipeRefresh)

}
