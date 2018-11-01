package com.ianarbuckle.restaurants.home.core.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.ianarbuckle.restaurants.home.core.interactor.RestaurantsInteractor
import com.ianarbuckle.restaurants.home.core.view.RestaurantsView
import com.ianarbuckle.restaurants.home.router.RestaurantsRouter
import com.ianarbuckle.restaurants.utils.RestaurantsBuddyDispatchers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import retrofit2.HttpException
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Ian Arbuckle on 20/07/2018.
 *
 */
class DefaultRestaurantsPresenter(private val view: RestaurantsView, private val interactor: RestaurantsInteractor, private val router: RestaurantsRouter,
                                  private val dispatchers: RestaurantsBuddyDispatchers, private val lifecycleOwner: LifecycleOwner) : RestaurantsPresenter, LifecycleObserver, CoroutineScope {

    private val subscriptions: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        job = Job()
        listenForDataFromNetwork()
        subscriptions.addAll(subscribeOnSwipeRefresh(), subscribeOnTryAgainClick())
    }

    private fun listenForDataFromNetwork() {
        view.showLoading()
        job = launch {
            try {
                fetchRestaurants()
            } catch (exception: HttpException) {
                handleHttpErrors(exception)
            } catch(throwable: Throwable) {
                view.showErrorState()
            } finally {
                view.hideLoading()
            }
        }
    }

    private suspend fun fetchRestaurants() {
        withContext(dispatchers.ui) {
            val results = interactor.fetchRestaurants()
                    .filter { it.results.isNotEmpty() }
                    .flatMap { it.results }.toMutableList()
            view.showRestaurants(results)
        }
    }

    private fun handleHttpErrors(exception: HttpException) {
        when (exception.code()) {
            500, 501, 502, 503, 504 -> view.showErrorState()
            400, 404 -> view.showEmptyState()
        }
    }

    private fun subscribeOnSwipeRefresh(): Disposable {
        return view.observeOnPullToRefresh()
                .doOnNext { view.hideLoading() }
                .subscribe {
                    this.listenForDataFromNetwork()
                    view.terminatePullToRefresh()
                }
    }

    private fun subscribeOnTryAgainClick(): Disposable {
        return view.observeOnTryAgainClick()
                .doOnNext { view.showLoading() }
                .subscribe {
                    this.listenForDataFromNetwork()
                }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        subscriptions.clear()
        lifecycleOwner.lifecycle.removeObserver(this)
        job.cancel()
    }

    override fun addLifecycleObserver() = lifecycleOwner.lifecycle.addObserver(this)
}