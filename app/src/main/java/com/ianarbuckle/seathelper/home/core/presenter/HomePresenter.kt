package com.ianarbuckle.seathelper.home.core.presenter

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
interface HomePresenter {
    fun onCreate()
    fun onDestroy()
    fun addLifecycleObserver()
}