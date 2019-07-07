package com.ianarbuckle.seathelper.home.core.view

import android.view.MenuItem
import android.view.View
import com.jakewharton.rxbinding2.InitialValueObservable
import io.reactivex.Observable

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
interface HomeView {
    fun getView(): View
    fun observeNavigationItemSelected(): Observable<MenuItem>
    fun menuItemClickListener(clickListener: (MenuItem) -> Boolean)
}