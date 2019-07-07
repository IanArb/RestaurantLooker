package com.ianarbuckle.seathelper.home.core.view

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.MenuItem
import android.view.View
import com.ianarbuckle.seathelper.R
import com.ianarbuckle.seathelper.extensions.active
import com.ianarbuckle.seathelper.utils.BottomNavigationPosition
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView
import com.jakewharton.rxbinding2.support.design.widget.itemSelections
import io.reactivex.Observable
import kotlinx.android.synthetic.main.home_view.view.*


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
        bottomNavigation.active(navPosition.position)
    }

    override fun getView(): View {
        return this
    }

    override fun observeNavigationItemSelected(): Observable<MenuItem> = RxBottomNavigationView.itemSelections(bottomNavigation)

    override fun menuItemClickListener(clickListener: (MenuItem) -> Boolean) {
        bottomNavigation.setOnNavigationItemSelectedListener {
            clickListener(it)
        }
    }

}
