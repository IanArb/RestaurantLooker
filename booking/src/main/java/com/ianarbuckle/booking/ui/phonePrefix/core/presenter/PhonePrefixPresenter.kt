package com.ianarbuckle.booking.ui.phonePrefix.core.presenter

import com.ianarbuckle.booking.ui.phonePrefix.core.interactor.PhonePrefixInteractor
import com.ianarbuckle.booking.ui.phonePrefix.core.router.PhonePrefixRouter
import com.ianarbuckle.booking.ui.phonePrefix.core.view.PhonePrefixView

/**
 * Created by Ian Arbuckle on 2019-09-03.
 *
 */
interface PhonePrefixPresenter {
    fun onCreate()
}

class PhonePrefixPresenterImpl(private val view: PhonePrefixView, private val interactor: PhonePrefixInteractor, private val router: PhonePrefixRouter) : PhonePrefixPresenter {

    override fun onCreate() {
        view.apply {
            showCountries(interactor.getCountries())
            countryItemClickListener {
                router.navigateBackWithPrefix(it.phoneCode)
            }
            toolbarBackClickListener {
                router.navigateBack()
            }
        }
    }
}