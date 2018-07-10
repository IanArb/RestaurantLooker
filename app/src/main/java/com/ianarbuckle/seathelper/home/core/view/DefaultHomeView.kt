package com.ianarbuckle.seathelper.home.core.view

import android.annotation.SuppressLint
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.MenuItem
import android.view.View
import com.ianarbuckle.seathelper.R
import com.ianarbuckle.seathelper.home.core.helper.BottomNavigationPosition
import kotlinx.android.synthetic.main.home_view.view.*
import com.ianarbuckle.seathelper.home.core.extension.active
import com.ianarbuckle.seathelper.home.core.extension.disableShiftMode
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView
import io.reactivex.Observable


@SuppressLint("ViewConstructor")
/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
class DefaultHomeView(context: Context?) : HomeView, ConstraintLayout(context) {

    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

    init {
        inflate(context, R.layout.home_view, this)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        bottomNavigation.disableShiftMode()
        bottomNavigation.active(navPosition.position)
    }

    override fun getView(): View {
        return this
    }

    override fun observeNavigationItemSelected(): Observable<MenuItem> = RxBottomNavigationView.itemSelections(bottomNavigation)

}
