package com.ianarbuckle.booking.ui.confirmation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ianarbuckle.booking.BookingProvider
import com.ianarbuckle.booking.ui.confirmation.presenter.ConfirmationPresenter
import com.ianarbuckle.booking.ui.confirmation.view.ConfirmationView
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
class ConfirmationActivity : AppCompatActivity() {

    @Inject
    lateinit var view: ConfirmationView

    @Inject
    lateinit var presenter: ConfirmationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BookingProvider.get().inject(this)
        setContentView(view.getView())
        presenter.onCreate()
    }

}