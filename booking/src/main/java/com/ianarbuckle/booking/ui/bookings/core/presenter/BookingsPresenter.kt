package com.ianarbuckle.booking.ui.bookings.core.presenter

import com.ianarbuckle.booking.ui.bookings.core.interactor.BookingsInteractor
import com.ianarbuckle.booking.ui.bookings.core.router.BookingsRouter
import com.ianarbuckle.booking.ui.bookings.core.view.BookingsView
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
interface BookingsPresenter {
    fun onCreate()
    fun onDestroy()
}

class BookingsPresenterImpl(private val view: BookingsView, private val interactor: BookingsInteractor, private val router: BookingsRouter) : BookingsPresenter, CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreate() {
        job = Job()
        fetchBookings()
        view.apply {
            tryAgainClickListener {
                fetchBookings()
            }
            toolbarClickListener {
                router.navigateBack()
            }
        }
        view.tryAgainClickListener {
            fetchBookings()
        }
    }

    private fun fetchBookings() {
        job = launch {
            try {
                withContext(Dispatchers.Main) {
                    view.showLoading()
                }
                val bookings = interactor.getBookings()
                withContext(Dispatchers.Main) {
                    bookings?.let { view.showBookings(it) }
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    view.showErrorMessage()
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