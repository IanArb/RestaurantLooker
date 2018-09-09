package com.ianarbuckle.restaurants.home.core.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.ianarbuckle.restaurants.home.core.interactor.RestaurantsInteractor
import com.ianarbuckle.restaurants.home.core.view.RestaurantsView
import com.ianarbuckle.restaurants.home.router.RestaurantsRouter
import com.ianarbuckle.restaurants.utils.CoroutineContextProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import retrofit2.HttpException

/**
 * Created by Ian Arbuckle on 20/07/2018.
 *
 */
class DefaultRestaurantsPresenter(private val view: RestaurantsView, private val interactor: RestaurantsInteractor, private val router: RestaurantsRouter,
                                  private val lifecycleOwner: LifecycleOwner, private val contextPool: CoroutineContextProvider) : RestaurantsPresenter, LifecycleObserver {

    private val subscriptions: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private lateinit var job: Job

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        fetchRestaurants()
        subscriptions.addAll(subscribeOnSwipeRefresh(), subscribeOnTryAgainClick())
    }

    private fun fetchRestaurants() {
        view.showLoading()
        job = launch(contextPool.main) {
            try {
                val response = interactor.fetchRestaurants()
                val results = response[0].results
                view.showRestaurants(results)
            } catch (exception: HttpException) {
                handleHttpErrors(exception)
            } catch(throwable: Throwable) {
                view.showErrorState()
            } finally {
                view.hideLoading()
            }
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
                    fetchRestaurants()
                    view.terminatePullToRefresh()
                }
    }

    private fun subscribeOnTryAgainClick(): Disposable {
        return view.observeOnTryAgainClick()
                .doOnNext { view.showLoading() }
                .subscribe {
                    fetchRestaurants()
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