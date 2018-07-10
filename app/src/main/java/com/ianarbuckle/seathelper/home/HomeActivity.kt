package com.ianarbuckle.seathelper.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ianarbuckle.seathelper.app.SeatHelperApplication
import com.ianarbuckle.seathelper.home.builder.DaggerHomeComponent

import com.ianarbuckle.seathelper.home.builder.HomeComponent
import com.ianarbuckle.seathelper.home.builder.HomeModule
import com.ianarbuckle.seathelper.home.core.presenter.HomePresenter
import com.ianarbuckle.seathelper.home.core.view.HomeView

import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var view: HomeView

    @Inject
    lateinit var presenter: HomePresenter

    private val component: HomeComponent by lazy {
        DaggerHomeComponent.builder().seatHelperAppComponent(SeatHelperApplication.component)
                .homeModule(HomeModule(this, this))
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.inject(this)

        setContentView(view.getView())

        presenter.addLifecycleObserver()
    }
}