package com.ianarbuckle.seathelper.splash.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.ianarbuckle.seathelper.R
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
interface SplashView {
    fun getView(): View
    fun showErrorToast()
}

class SplashViewImpl(context: Context) : SplashView, ConstraintLayout(context) {

    init {
        inflate(context, R.layout.splash_view, this)
    }

    override fun getView(): View = this

    override fun showErrorToast() {
        Toast.makeText(context, "Error while trying to authenticate", Toast.LENGTH_SHORT).show()
    }
}