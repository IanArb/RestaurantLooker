package com.ianarbuckle.restaurants.home.core.view

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.ianarbuckle.restaurants.R
import com.ianarbuckle.restaurants.home.core.view.adapter.HomeAdapter
import com.ianarbuckle.restaurants.home.model.Restaurants
import com.jakewharton.rxbinding2.support.v4.widget.refreshes
import com.jakewharton.rxbinding2.view.clicks
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
        errorMessage.text = resources.getString(R.string.error_message_empty)
        recyclerView.visibility = View.GONE
        errorMessage.visibility = View.VISIBLE
        errorImageView.visibility = View.VISIBLE
        tryAgainButton.visibility = View.GONE
        progressBar.visibility = View.GONE
    }

    override fun showErrorState() {
        errorMessage.text = resources.getString(R.string.error_message)
        recyclerView.visibility = View.GONE
        errorMessage.visibility = View.VISIBLE
        errorImageView.visibility = View.VISIBLE
        tryAgainButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun terminatePullToRefresh() {
        swipeRefresh.isRefreshing = false
    }

    override fun observeOnTryAgainClick(): Observable<Unit> = tryAgainButton.clicks()

    override fun observeOnPullToRefresh(): Observable<Unit> = swipeRefresh.refreshes()

}
