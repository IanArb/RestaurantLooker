package com.ianarbuckle.restaurants.ui.menu.core.view

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.ianarbuckle.restaurant.R
import com.ianarbuckle.restaurants.data.Dish
import com.ianarbuckle.restaurants.ui.menu.core.view.adapter.MenuAdapter
import com.ianarbuckle.restaurants.utils.provideImage
import kotlinx.android.synthetic.main.menu_restaurants_view.view.*

/**
 * Created by Ian Arbuckle on 2019-04-23.
 *
 */
interface MenuView {
    fun getView(): View
    fun showMenu(dish: MutableList<Dish>)
    fun showImageBanner(url: String)
    fun showToolbarTitle(title: String)
    fun toolbarClickListener(clickListener: () -> Unit)
}

class DefaultMenuView(context: Context) : MenuView, FrameLayout(context) {

    init {
        inflate(context, R.layout.menu_restaurants_view, this)
    }

    override fun getView(): View = this

    override fun showMenu(dish: MutableList<Dish>) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MenuAdapter(dish)
        }
    }

    override fun showImageBanner(url: String) {
        toolbarImage.provideImage(context, url)
    }

    override fun showToolbarTitle(title: String) {
        toolbarTitle.text = title
    }

    override fun toolbarClickListener(clickListener: () -> Unit) {
        home.setOnClickListener { clickListener() }
    }
}