package com.ianarbuckle.seathelper.home.core.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.ianarbuckle.seathelper.utils.BottomNavigationPosition
import com.ianarbuckle.seathelper.utils.findNavigationById
import com.ianarbuckle.seathelper.home.core.view.HomeView
import com.ianarbuckle.seathelper.home.router.HomeRouter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
class DefaultHomePresenter(private val view: HomeView, private val router: HomeRouter, private val lifecycleOwner: LifecycleOwner) : HomePresenter, LifecycleObserver {

    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

    private val subscriptions: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        subscriptions.addAll(subscribeOnBottomNavigationItemSelections())
//        navigateToNavBarItemSelected()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        subscriptions.clear()
        lifecycleOwner.lifecycle.removeObserver(this)
    }

    private fun subscribeOnBottomNavigationItemSelections(): Disposable {
        return view.observeNavigationItemSelected()
                .subscribe {
                    navPosition = findNavigationById(it.itemId)
                    router.switchFragment(navPosition)
                }
    }

    private fun navigateToNavBarItemSelected() {
        view.menuItemClickListener {
            navPosition = findNavigationById(it.itemId)
            router.switchFragment(navPosition)
        }
    }

    override fun addLifecycleObserver() = lifecycleOwner.lifecycle.addObserver(this)

}