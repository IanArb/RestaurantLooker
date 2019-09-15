package com.ianarbuckle.booking.ui.confirmation.presenter

import com.ianarbuckle.booking.ui.confirmation.interactor.ConfirmationInteractor
import com.ianarbuckle.booking.ui.confirmation.router.ConfirmationRouter
import com.ianarbuckle.booking.ui.confirmation.view.ConfirmationView

/**
 * Created by Ian Arbuckle on 2019-09-10.
 *
 */
interface ConfirmationPresenter {
    fun onCreate()
}

class ConfirmationPresenterImpl(private val view: ConfirmationView, private val interactor: ConfirmationInteractor, private val router: ConfirmationRouter) : ConfirmationPresenter {

    override fun onCreate() {
        view.apply {
            showBooking(interactor.getBooking())
            onBookingsClickListener {
                router.navigateToBookings()
            }
        }
    }
}