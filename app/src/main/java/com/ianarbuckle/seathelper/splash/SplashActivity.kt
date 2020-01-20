package com.ianarbuckle.seathelper.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ianarbuckle.client.AuthenticationClient
import com.ianarbuckle.seathelper.app.RestaurantBuddyApplication
import com.ianarbuckle.seathelper.splash.builder.DaggerSplashComponent
import com.ianarbuckle.seathelper.splash.builder.SplashModule
import com.ianarbuckle.seathelper.splash.core.presenter.SplashPresenter
import com.ianarbuckle.seathelper.splash.core.view.SplashView
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: SplashPresenter

    @Inject
    lateinit var view: SplashView

    @Inject
    lateinit var authenticationClient: AuthenticationClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerSplashComponent.builder()
                .appComponent(RestaurantBuddyApplication.component)
                .splashModule(SplashModule(this))
                .build()
                .inject(this)

        setContentView(view.getView())

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}