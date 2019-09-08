package com.ianarbuckle.booking.ui.phonePrefix

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ianarbuckle.booking.BookingProvider
import com.ianarbuckle.booking.builder.BookingInjector
import com.ianarbuckle.booking.ui.phonePrefix.core.presenter.PhonePrefixPresenter
import com.ianarbuckle.booking.ui.phonePrefix.core.view.PhonePrefixView
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 2019-09-03.
 *
 */
class PhonePrefixActivity : AppCompatActivity() {

    @Inject
    lateinit var view: PhonePrefixView

    @Inject
    lateinit var presenter: PhonePrefixPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BookingProvider.get().inject(this)
        setContentView(view.getView())
        presenter.onCreate()
    }

}