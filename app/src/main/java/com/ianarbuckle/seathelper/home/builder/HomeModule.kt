package com.ianarbuckle.seathelper.home.builder

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.ianarbuckle.seathelper.home.HomeActivity
import com.ianarbuckle.seathelper.home.core.view.adapter.ViewPagerAdapter
import com.ianarbuckle.seathelper.home.core.presenter.DefaultHomePresenter
import com.ianarbuckle.seathelper.home.core.presenter.HomePresenter
import com.ianarbuckle.seathelper.home.core.view.DefaultHomeView
import com.ianarbuckle.seathelper.home.core.view.HomeView
import com.ianarbuckle.seathelper.home.router.DefaultHomeRouter
import com.ianarbuckle.seathelper.home.router.HomeRouter
import dagger.Module
import dagger.Provides

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
@Module
class HomeModule(private val activity: HomeActivity, private val lifecycleOwner: LifecycleOwner) {

    @HomeScope
    @Provides
    fun provideContext(): Context = activity

    @HomeScope
    @Provides
    fun provideView(): HomeView = DefaultHomeView(activity)

    @HomeScope
    @Provides
    fun providePresenter(view: HomeView, router: HomeRouter): HomePresenter
            = DefaultHomePresenter(view, router, lifecycleOwner)

    @HomeScope
    @Provides
    fun provideRouter(): HomeRouter = DefaultHomeRouter(activity.supportFragmentManager)

    @HomeScope
    @Provides
    fun provideAdapter(): ViewPagerAdapter = ViewPagerAdapter(activity.supportFragmentManager)

}