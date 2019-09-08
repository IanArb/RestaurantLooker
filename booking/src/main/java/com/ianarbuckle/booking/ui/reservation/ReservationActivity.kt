package com.ianarbuckle.booking.ui.reservation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ianarbuckle.booking.BookingProvider
import com.ianarbuckle.booking.ui.reservation.core.presenter.ReservationPresenter
import com.ianarbuckle.booking.ui.reservation.core.view.ReservationView
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 2019-08-24.
 *
 */
class ReservationActivity : AppCompatActivity() {

    @Inject
    lateinit var view: ReservationView

    @Inject
    lateinit var presenter: ReservationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BookingProvider.get().inject(this)
        setContentView(view.getView())
        presenter.onCreate()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let { presenter.onSavedInstanceState(it) }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.let { presenter.onRestoreInstanceState(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}