package com.ianarbuckle.booking.ui.reservation

import android.content.Intent
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

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        presenter.onSavedInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.onRestoreInstanceState(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}