package com.ianarbuckle.seathelper.home.core.presenter

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
class DefaultHomePresenter(private val view: HomeView, private val router: HomeRouter) : HomePresenter {

    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

    private val subscriptions: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate() {
        subscriptions.addAll(subscribeOnBottomNavigationItemSelections())
    }

    override fun onDestroy() {
        subscriptions.clear()
    }

    private fun subscribeOnBottomNavigationItemSelections(): Disposable {
        return view.observeNavigationItemSelected()
                .subscribe {
                    navPosition = findNavigationById(it.itemId)
                    router.switchFragment(navPosition)
                }
    }

}