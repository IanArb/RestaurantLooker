package com.ianarbuckle.restaurants.ui.menu.core.presenter

import androidx.lifecycle.LifecycleObserver
import com.ianarbuckle.restaurants.ui.menu.core.interactor.MenuInteractor
import com.ianarbuckle.restaurants.ui.menu.core.router.MenuRouter
import com.ianarbuckle.restaurants.ui.menu.core.view.MenuView

/**
 * Created by Ian Arbuckle on 2019-04-23.
 *
 */
interface MenuPresenter {
    fun onCreate()
}

class DefaultMenuPresenter(private val view: MenuView, private val interactor: MenuInteractor, private val router: MenuRouter) : MenuPresenter, LifecycleObserver {

    override fun onCreate() {
        view.showImageBanner(interactor.getImageBannerUrl())
        view.showToolbarTitle(interactor.getRestaurantName())
        view.showMenu(interactor.getMenu())
        view.toolbarClickListener { router.navigateBack() }
    }

}