package com.ianarbuckle.booking.ui.booking.core.presenter

import com.ianarbuckle.booking.ui.booking.core.view.BookingView
import com.ianarbuckle.booking.ui.booking.core.interactor.BookingInteractor
import com.ianarbuckle.booking.ui.booking.core.router.BookingRouter
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

interface BookingPresenter {
    fun onCreate()
    fun onDestroy()
}

class BookingPresenterImpl(private val view: BookingView, private val interactor: BookingInteractor, private val router: BookingRouter) : BookingPresenter, CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreate() {
        job = Job()
        fetchBooking()
        view.apply {
            viewBookingsClickListener {
                router.navigateToViewAllBookings()
            }
        }
    }

    private fun fetchBooking() {
        view.showLoading()
        launch {
            try {
                val booking = interactor.fetchBooking()
                withContext(Dispatchers.Main) {
                    booking?.let {
                        view.showBooking(it)
                    }
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    view.showEmptyState()
                }
            } finally {
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                }
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
    }
}