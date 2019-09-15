package com.ianarbuckle.tablemap.ui.core.presenter

import com.ianarbuckle.tablemap.ui.core.interactor.TablesMapInteractor
import com.ianarbuckle.tablemap.ui.core.view.TablesMapView
import com.ianarbuckle.tablemap.ui.router.TablesMapRouter
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Ian Arbuckle on 2019-07-20.
 *
 */
interface TablesMapPresenter {
    fun onCreate()
    fun onDestroy()
}

class TablesMapPresenterImpl(private val view: TablesMapView, private val interactor: TablesMapInteractor, private val router: TablesMapRouter) : TablesMapPresenter, CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreate() {
        job = launch {
            val tables = interactor.fetchTablesById()
            withContext(Dispatchers.Main) {
                view.showTableRow(tables.row)
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
    }
}