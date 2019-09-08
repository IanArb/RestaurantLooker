package com.ianarbuckle.booking.ui.phonePrefix.builder

import android.content.Context
import com.ianarbuckle.booking.ui.phonePrefix.PhonePrefixActivity
import com.ianarbuckle.booking.ui.phonePrefix.core.interactor.PhonePrefixInteractor
import com.ianarbuckle.booking.ui.phonePrefix.core.interactor.PhonePrefixInteractorImpl
import com.ianarbuckle.booking.ui.phonePrefix.core.presenter.PhonePrefixPresenter
import com.ianarbuckle.booking.ui.phonePrefix.core.presenter.PhonePrefixPresenterImpl
import com.ianarbuckle.booking.ui.phonePrefix.core.router.PhonePrefixRouter
import com.ianarbuckle.booking.ui.phonePrefix.core.router.PhonePrefixRouterImpl
import com.ianarbuckle.booking.ui.phonePrefix.core.view.PhonePrefixView
import com.ianarbuckle.booking.ui.phonePrefix.core.view.PhonePrefixViewImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

/**
 * Created by Ian Arbuckle on 2019-09-03.
 *
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PhonePrefixBuilder

@Module
class PhonePrefixModule(private val activity: PhonePrefixActivity) {

    @PhonePrefixBuilder
    @Provides
    fun provideContext(): Context = activity

    @PhonePrefixBuilder
    @Provides
    fun provideView(context: Context): PhonePrefixView = PhonePrefixViewImpl(context)

    @PhonePrefixBuilder
    @Provides
    fun provideInteractor(): PhonePrefixInteractor = PhonePrefixInteractorImpl(activity)

    @PhonePrefixBuilder
    @Provides
    fun providePresenter(view: PhonePrefixView, interactor: PhonePrefixInteractor, router: PhonePrefixRouter): PhonePrefixPresenter
            = PhonePrefixPresenterImpl(view, interactor, router)

    @PhonePrefixBuilder
    @Provides
    fun provideRouter(): PhonePrefixRouter = PhonePrefixRouterImpl(activity)

}

@PhonePrefixBuilder
@Component(modules = [PhonePrefixModule::class])
interface PhonePrefixComponent {
    fun inject(activity: PhonePrefixActivity)
}