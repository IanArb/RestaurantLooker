package com.ianarbuckle.restaurants.home.builder

import android.content.Context
import com.ianarbuckle.restaurants.home.RestaurantsFragment
import com.ianarbuckle.restaurants.home.core.interactor.DefaultRestaurantsInteractor
import com.ianarbuckle.restaurants.home.core.interactor.RestaurantsInteractor
import com.ianarbuckle.restaurants.home.core.presenter.DefaultRestaurantsPresenter
import com.ianarbuckle.restaurants.home.core.presenter.RestaurantsPresenter
import com.ianarbuckle.restaurants.network.repository.RestaurantsRepository
import com.ianarbuckle.restaurants.home.core.view.DefaultRestaurantsView
import com.ianarbuckle.restaurants.home.core.view.RestaurantsView
import com.ianarbuckle.restaurants.home.router.DefaultRestaurantsRouter
import com.ianarbuckle.restaurants.home.router.RestaurantsRouter
import com.ianarbuckle.restaurants.utils.RestaurantsBuddyDispatchers
import dagger.Module
import dagger.Provides

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
@Module
class HomeModule(private val fragment: RestaurantsFragment) {

    @HomeScope
    @Provides
    fun provideContext(): Context? = fragment.context

    @HomeScope
    @Provides
    fun provideView(context: Context?): RestaurantsView = DefaultRestaurantsView(context)

    @HomeScope
    @Provides
    fun provideInteractor(repository: RestaurantsRepository): RestaurantsInteractor = DefaultRestaurantsInteractor(repository)

    @HomeScope
    @Provides
    fun providePresenter(view: RestaurantsView, interactor: RestaurantsInteractor, router: RestaurantsRouter, dispatchers: RestaurantsBuddyDispatchers): RestaurantsPresenter
            = DefaultRestaurantsPresenter(view, interactor, router, dispatchers, fragment)

    @HomeScope
    @Provides
    fun provideRouter(context: Context?): RestaurantsRouter = DefaultRestaurantsRouter(context)

    @HomeScope
    @Provides
    fun provideDispatchers(): RestaurantsBuddyDispatchers = RestaurantsBuddyDispatchers()
}