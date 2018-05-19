package com.ianarbuckle.seathelper.home.core.presenter

import com.ianarbuckle.seathelper.home.core.interactor.HomeInteractor
import com.ianarbuckle.seathelper.home.core.view.HomeView
import com.ianarbuckle.seathelper.home.router.HomeRouter
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
class DefaultHomePresenter(private var view: HomeView, private var interactor: HomeInteractor,
                           private var router: HomeRouter) : HomePresenter {

    val subscriptions: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreate() {

    }

    override fun onDestroy() {
        subscriptions.clear()
    }
}