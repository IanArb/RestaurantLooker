package com.ianarbuckle.restaurants.ui.home.builder

import android.content.Context
import com.ianarbuckle.restaurants.ui.home.RestaurantsFragment
import com.ianarbuckle.restaurants.ui.home.core.interactor.DefaultRestaurantsInteractor
import com.ianarbuckle.restaurants.ui.home.core.interactor.RestaurantsInteractor
import com.ianarbuckle.restaurants.ui.home.core.presenter.DefaultRestaurantsPresenter
import com.ianarbuckle.restaurants.ui.home.core.presenter.RestaurantsPresenter
import com.ianarbuckle.restaurants.ui.home.core.repository.RestaurantsRepository
import com.ianarbuckle.restaurants.ui.home.core.view.DefaultRestaurantsView
import com.ianarbuckle.restaurants.ui.home.core.view.RestaurantsView
import com.ianarbuckle.restaurants.ui.home.router.DefaultRestaurantsRouter
import com.ianarbuckle.restaurants.ui.home.router.RestaurantsRouter
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
    fun provideContext(): Context = fragment.requireContext()

    @HomeScope
    @Provides
    fun provideView(context: Context): RestaurantsView = DefaultRestaurantsView(context)

    @HomeScope
    @Provides
    fun provideInteractor(repository: RestaurantsRepository): RestaurantsInteractor = DefaultRestaurantsInteractor(repository)

    @HomeScope
    @Provides
    fun providePresenter(view: RestaurantsView, interactor: RestaurantsInteractor, router: RestaurantsRouter): RestaurantsPresenter
            = DefaultRestaurantsPresenter(view, interactor, router, fragment)

    @HomeScope
    @Provides
    fun provideRouter(context: Context): RestaurantsRouter = DefaultRestaurantsRouter(context)
}