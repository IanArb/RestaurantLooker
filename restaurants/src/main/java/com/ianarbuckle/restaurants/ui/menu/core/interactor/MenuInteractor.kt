package com.ianarbuckle.restaurants.ui.menu.core.interactor

import android.app.Activity
import com.ianarbuckle.models.restaurant.Dish
import com.ianarbuckle.models.restaurant.Restaurant
import com.ianarbuckle.restaurants.utils.Constants

/**
 * Created by Ian Arbuckle on 2019-04-23.
 *
 */
interface MenuInteractor {
    fun getMenu(): List<Dish>
    fun getRestaurantName(): String
    fun getImageBannerUrl(): String
}

class DefaultMenuInteractor(private val activity: Activity) : MenuInteractor {

    override fun getRestaurantName(): String =
            activity.intent.getParcelableExtra<Restaurant>(Constants.RESTAURANT_KEY).restaurantName

    override fun getImageBannerUrl(): String =
            activity.intent.getParcelableExtra<Restaurant>(Constants.RESTAURANT_KEY).imageUrl

    override fun getMenu(): List<Dish> =
            activity.intent.getParcelableArrayListExtra(Constants.DISHES_KEY)

}