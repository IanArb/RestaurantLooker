package com.ianarbuckle.restaurants.ui.menu.core.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.ianarbuckle.restaurants.ui.menu.core.interactor.MenuInteractor
import com.ianarbuckle.restaurants.ui.menu.core.router.MenuRouter
import com.ianarbuckle.restaurants.ui.menu.core.view.MenuView

/**
 * Created by Ian Arbuckle on 2019-04-23.
 *
 */
interface MenuPresenter {
    fun onCreate()
    fun addLifecycleObserver()
    fun onDestroy()
}

class DefaultMenuPresenter(private val view: MenuView, private val interactor: MenuInteractor, private val router: MenuRouter,
                           private val lifecycleOwner: LifecycleOwner) : MenuPresenter, LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        view.showImageBanner(interactor.getImageBannerUrl())
        view.showToolbarTitle(interactor.getRestaurantName())
        view.showMenu(interactor.getMenu())
        view.toolbarClickListener { router.navigateBack() }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        lifecycleOwner.lifecycle.removeObserver(this)
    }

    override fun addLifecycleObserver() {
        lifecycleOwner.lifecycle.addObserver(this)
    }

}