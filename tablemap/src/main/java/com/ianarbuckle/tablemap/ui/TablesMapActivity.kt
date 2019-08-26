package com.ianarbuckle.tablemap.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ianarbuckle.tablemap.TablesMapProvider
import com.ianarbuckle.tablemap.ui.core.presenter.TablesMapPresenter
import com.ianarbuckle.tablemap.ui.core.view.TablesMapView
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 2019-07-20.
 *
 */
class TablesMapActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: TablesMapPresenter

    @Inject
    lateinit var view: TablesMapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TablesMapProvider.get().inject(this)

        view.getView()

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}