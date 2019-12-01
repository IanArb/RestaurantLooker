package com.ianarbuckle.booking.ui.confirmation.builder

import android.app.Activity
import android.content.Context
import com.ianarbuckle.booking.BookingNavigator
import com.ianarbuckle.booking.ui.confirmation.ConfirmationActivity
import com.ianarbuckle.booking.ui.confirmation.interactor.ConfirmationInteractor
import com.ianarbuckle.booking.ui.confirmation.interactor.ConfirmationInteractorImpl
import com.ianarbuckle.booking.ui.confirmation.presenter.ConfirmationPresenter
import com.ianarbuckle.booking.ui.confirmation.presenter.ConfirmationPresenterImpl
import com.ianarbuckle.booking.ui.confirmation.router.ConfirmationRouter
import com.ianarbuckle.booking.ui.confirmation.router.ConfirmationRouterImpl
import com.ianarbuckle.booking.ui.confirmation.view.ConfirmationView
import com.ianarbuckle.booking.ui.confirmation.view.ConfirmationViewImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ConfirmationScope

@Module
class ConfirmationModule(private val activity: ConfirmationActivity, private val bookingsCallback: ((ConfirmationActivity) -> Unit)? = null) {

    @ConfirmationScope
    @Provides
    fun provideContext(): Context = activity

    @ConfirmationScope
    @Provides
    fun provideView(context: Context): ConfirmationView = ConfirmationViewImpl(context)

    @ConfirmationScope
    @Provides
    fun provideInteractor(): ConfirmationInteractor = ConfirmationInteractorImpl(activity)

    @ConfirmationScope
    @Provides
    fun providePresenter(view: ConfirmationView, interactor: ConfirmationInteractor, router: ConfirmationRouter): ConfirmationPresenter
            = ConfirmationPresenterImpl(view, interactor, router)

    @ConfirmationScope
    @Provides
    fun provideRouter(): ConfirmationRouter = ConfirmationRouterImpl(activity, bookingsCallback)

}

@ConfirmationScope
@Component(modules = [ConfirmationModule::class])
interface ConfirmationComponent {
    fun inject(activity: ConfirmationActivity)
}
