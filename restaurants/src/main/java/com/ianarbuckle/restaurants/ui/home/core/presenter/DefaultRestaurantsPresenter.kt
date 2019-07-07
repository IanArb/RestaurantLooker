package com.ianarbuckle.restaurants.ui.home.core.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.ianarbuckle.restaurants.data.Restaurant
import com.ianarbuckle.restaurants.ui.home.core.interactor.RestaurantsInteractor
import com.ianarbuckle.restaurants.ui.home.core.view.RestaurantsView
import com.ianarbuckle.restaurants.ui.home.router.RestaurantsRouter
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Ian Arbuckle on 20/07/2018.
 *
 */
class DefaultRestaurantsPresenter(private val view: RestaurantsView, private val interactor: RestaurantsInteractor, private val router: RestaurantsRouter,
                                  private val lifecycleOwner: LifecycleOwner) : RestaurantsPresenter, LifecycleObserver, CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        job = Job()
        fetchRestuarants()
        view.executeTryAgainClick {
            fetchRestuarants()
        }
    }

    private fun fetchRestuarants() {
        view.showLoading()
        job = launch {
            try {
                val localResults = interactor.getSavedRestaurants()
                if (localResults.isNullOrEmpty()) {
                    val results = interactor.fetchRestaurants()
                    interactor.saveRestaurants(results)
                    withContext(Dispatchers.Main) {
                        showResults(results)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        showResults(localResults)
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

    private fun showResults(results: MutableList<Restaurant>) {
        if (results.isNullOrEmpty()) {
            view.showEmptyState()
        } else {
            view.showRestaurants(results)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        lifecycleOwner.lifecycle.removeObserver(this)
        job.cancel()
    }

    override fun addLifecycleObserver() = lifecycleOwner.lifecycle.addObserver(this)

}