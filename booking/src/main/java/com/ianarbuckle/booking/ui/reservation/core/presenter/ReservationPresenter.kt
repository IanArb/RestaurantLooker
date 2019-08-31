package com.ianarbuckle.booking.ui.reservation.core.presenter

import com.ianarbuckle.booking.ui.reservation.core.interactor.ReservationInteractor
import com.ianarbuckle.booking.ui.reservation.core.router.ReservationRouter
import com.ianarbuckle.booking.ui.reservation.core.view.ReservationView

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface ReservationPresenter {
    fun onCreate()
    fun onDestroy()
}

class ReservationPresenterImpl(private val view: ReservationView, private val interactor: ReservationInteractor, private val router: ReservationRouter) : ReservationPresenter {

    override fun onCreate() {

    }

    override fun onDestroy() {

    }
}