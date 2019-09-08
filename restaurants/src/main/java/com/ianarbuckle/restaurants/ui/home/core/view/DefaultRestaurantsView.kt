package com.ianarbuckle.restaurants.ui.home.core.view

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ianarbuckle.core.extensions.getColorFromAttr
import com.ianarbuckle.restaurant.R
import com.ianarbuckle.restaurants.ui.home.core.view.adapter.RestaurantsAdapter
import com.ianarbuckle.restaurants.data.Restaurant
import com.ianarbuckle.restaurants.ui.menu.MenuActivity
import com.ianarbuckle.restaurants.utils.Constants
import kotlinx.android.synthetic.main.home_restaurants_view.view.*
import java.util.ArrayList

/**
 * Created by Ian Arbuckle on 20/07/2018.
 *
 */
class DefaultRestaurantsView(context: Context) : RestaurantsView, ConstraintLayout(context) {

    lateinit var restaurantsAdapter: RestaurantsAdapter

    init {
        inflate(context, R.layout.home_restaurants_view, this)
        toolbar.apply {
            title = resources.getString(R.string.title_restaurants)
            val toolbarAttributeColor = context.getColorFromAttr(R.attr.titleTextColor)
            setTitleTextColor(toolbarAttributeColor)
        }
    }

    override fun getView(): View {
        return this
    }

    override fun showRestaurants(restaurants: MutableList<Restaurant>) {
        recyclerView.visibility = View.VISIBLE
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        restaurantsAdapter = RestaurantsAdapter(restaurants)
        recyclerView.adapter = restaurantsAdapter
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
        errorImageView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun executeTryAgainClick(clickListener: () -> Unit) {
        tryAgainButton.setOnClickListener {
            clickListener()
        }
    }

    override fun onMenuClickListener(menuClickListener: (Restaurant) -> Unit) {
        restaurantsAdapter.onMenuClickListener = menuClickListener
    }

    override fun onBookingClickListener(bookClickListener: (Restaurant) -> Unit) {
        restaurantsAdapter.onBookingClickListener = bookClickListener
    }

}
