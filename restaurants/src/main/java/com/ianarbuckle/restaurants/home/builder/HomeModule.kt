package com.ianarbuckle.restaurants.home.builder

import android.arch.lifecycle.LifecycleOwner
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
import com.ianarbuckle.restaurants.utils.CoroutineContextProvider
import dagger.Module
import dagger.Provides

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
@Module
class HomeModule(private val fragment: RestaurantsFragment, private val lifecycleOwner: LifecycleOwner) {

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
    fun providePresenter(view: RestaurantsView, interactor: RestaurantsInteractor, router: RestaurantsRouter, coroutineContextProvider: CoroutineContextProvider): RestaurantsPresenter
            = DefaultRestaurantsPresenter(view, interactor, router, lifecycleOwner, coroutineContextProvider)

    @HomeScope
    @Provides
    fun provideRouter(): RestaurantsRouter = DefaultRestaurantsRouter()

    @HomeScope
    @Provides
    fun provideCoroutineContextProvider(): CoroutineContextProvider = CoroutineContextProvider()
}