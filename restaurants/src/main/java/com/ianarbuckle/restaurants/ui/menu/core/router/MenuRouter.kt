package com.ianarbuckle.restaurants.ui.menu.core.router

import android.app.Activity

/**
 * Created by Ian Arbuckle on 2019-05-25.
 *
 */
interface MenuRouter {
    fun navigateBack()
}

class DefaultMenuRouter(private val activity: Activity) : MenuRouter {

    override fun navigateBack() {
        activity.finish()
    }
}