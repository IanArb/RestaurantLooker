package com.ianarbuckle.seathelper.home.core.view

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.ianarbuckle.seathelper.R

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
class DefaultHomeView(context: Context?) : HomeView, LinearLayout(context) {


    override fun getView(): View {
        inflate(context, R.layout.home_view, this)
        return this
    }

}