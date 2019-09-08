package com.ianarbuckle.booking.ui.calendar.builder

import android.content.Context
import com.ianarbuckle.booking.ui.calendar.CalendarActivity
import com.ianarbuckle.booking.ui.calendar.core.presenter.CalendarPresenter
import com.ianarbuckle.booking.ui.calendar.core.presenter.CalendarPresenterImpl
import com.ianarbuckle.booking.ui.calendar.core.router.CalendarRouter
import com.ianarbuckle.booking.ui.calendar.core.router.CalendarRouterImpl
import com.ianarbuckle.booking.ui.calendar.core.view.CalendarView
import com.ianarbuckle.booking.ui.calendar.core.view.CalendarViewImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

/**
 * Created by Ian Arbuckle on 2019-09-02.
 *
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CalendarScope

@Module
class CalendarModule(private val activity: CalendarActivity) {

    @CalendarScope
    @Provides
    fun provideContext(): Context = activity

    @CalendarScope
    @Provides
    fun provideView(context: Context): CalendarView = CalendarViewImpl(context)

    @CalendarScope
    @Provides
    fun providePresenter(view: CalendarView, router: CalendarRouter): CalendarPresenter = CalendarPresenterImpl(view, router)

    @CalendarScope
    @Provides
    fun provideRouter(): CalendarRouter = CalendarRouterImpl(activity)
}

@CalendarScope
@Component(modules = [CalendarModule::class])
interface CalendarComponent {
    fun inject(activity: CalendarActivity)
}