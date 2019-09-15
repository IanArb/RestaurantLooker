package com.ianarbuckle.restaurants.ui.home.core.presenter

import com.ianarbuckle.models.restaurant.Restaurant
import com.ianarbuckle.restaurants.ui.home.core.interactor.RestaurantsInteractor
import com.ianarbuckle.restaurants.ui.home.core.view.RestaurantsView
import com.ianarbuckle.restaurants.ui.home.router.RestaurantsRouter
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Ian Arbuckle on 20/07/2018.
 *
 */
class DefaultRestaurantsPresenter(private val view: RestaurantsView, private val interactor: RestaurantsInteractor, private val router: RestaurantsRouter) : RestaurantsPresenter, CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreate() {
        job = Job()
        fetchRestaurants()
        view.executeTryAgainClick {
            fetchRestaurants()
        }
    }

    private fun fetchRestaurants() {
        view.showLoading()
        job = launch {
            try {
                val localResults = interactor.getSavedRestaurants()
                if (localResults.isNullOrEmpty()) {
                    val results = interactor.fetchRestaurants()
                    interactor.saveRestaurants(results)
                    withContext(Dispatchers.Main) {
                        showResults(results)
                        onMenuClick()
                        onBookClick()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        showResults(localResults)
                        onMenuClick()
                        onBookClick()
                    }
                }
            } catch (exception: Throwable) {
                withContext(Dispatchers.Main) {
                    view.showErrorState()
                }
            } finally {
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                }
            }
        }
    }

    private fun onMenuClick() {
        view.onMenuClickListener {
            router.navigateMenu(it)
        }
    }

    private fun onBookClick() {
        view.onBookingClickListener {
            router.navigateToBookingReservation(it)
        }
    }

    private fun showResults(results: MutableList<Restaurant>) {
        if (results.isNullOrEmpty()) {
            view.showEmptyState()
        } else {
            view.showRestaurants(results)
        }
    }

    override fun onDestroy() {
        job.cancel()
    }

}