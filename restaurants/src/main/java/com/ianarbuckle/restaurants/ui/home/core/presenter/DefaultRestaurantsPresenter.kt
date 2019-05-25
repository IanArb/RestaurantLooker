package com.ianarbuckle.restaurants.ui.home.core.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.ianarbuckle.restaurants.ui.home.core.interactor.RestaurantsInteractor
import com.ianarbuckle.restaurants.ui.home.core.view.RestaurantsView
import kotlinx.coroutines.*
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

/**
 * Created by Ian Arbuckle on 20/07/2018.
 *
 */
class DefaultRestaurantsPresenter(private val view: RestaurantsView, private val interactor: RestaurantsInteractor,
                                  private val lifecycleOwner: LifecycleOwner) : RestaurantsPresenter, LifecycleObserver, CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        job = Job()
        fetchRestuarants()
        handleOnTryAgainClick()
    }

    private fun fetchRestuarants() {
        view.showLoading()
        job = launch {
            try {
                val results = interactor.fetchRestaurants()
                withContext(Dispatchers.Main) {
                    view.showRestaurants(results)
                }
            } catch (exception: HttpException) {
                handleHttpErrors(exception)
            } finally {
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                }
            }
        }
    }

    private fun handleHttpErrors(exception: HttpException) {
        when (exception.code()) {
            500, 501, 502, 503, 504 -> view.showErrorState()
            400, 404 -> view.showEmptyState()
        }
    }

    private fun handleOnTryAgainClick() {
        view.executeTryAgainClick {
            fetchRestuarants()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        lifecycleOwner.lifecycle.removeObserver(this)
        job.cancel()
    }

    override fun addLifecycleObserver() = lifecycleOwner.lifecycle.addObserver(this)
}