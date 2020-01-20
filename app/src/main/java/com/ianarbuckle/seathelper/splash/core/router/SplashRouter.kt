package com.ianarbuckle.seathelper.splash.core.router

import android.app.Activity
import android.content.Intent
import com.ianarbuckle.seathelper.home.HomeActivity
import javax.inject.Inject

interface SplashRouter {

    fun navigateToHomeScreen()
    fun closeScreen()
}

class SplashRouterImpl(private val activity: Activity): SplashRouter {

    override fun navigateToHomeScreen() {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
    }

    override fun closeScreen() {
        activity.finish()
    }

}