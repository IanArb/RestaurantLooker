package com.ianarbuckle.booking.ui.bookings.builder

import android.content.Context
import com.ianarbuckle.booking.network.builder.NetworkComponent
import com.ianarbuckle.booking.ui.bookings.BookingsActivity
import com.ianarbuckle.booking.ui.bookings.core.interactor.BookingsInteractor
import com.ianarbuckle.booking.ui.bookings.core.interactor.BookingsInteractorImpl
import com.ianarbuckle.booking.ui.bookings.core.presenter.BookingsPresenter
import com.ianarbuckle.booking.ui.bookings.core.presenter.BookingsPresenterImpl
import com.ianarbuckle.booking.network.repository.BookingsRepository
import com.ianarbuckle.booking.ui.bookings.core.router.BookingsRouter
import com.ianarbuckle.booking.ui.bookings.core.router.BookingsRouterImpl
import com.ianarbuckle.booking.ui.bookings.core.view.BookingsView
import com.ianarbuckle.booking.ui.bookings.core.view.BookingsViewImpl
import com.ianarbuckle.core.utils.DeviceUuidFactory
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
annotation class BookingsScope

@Module
class BookingsModule(private val activity: BookingsActivity, private val uuidFactory: DeviceUuidFactory) {

    @BookingsScope
    @Provides
    fun provideContext(): Context = activity

    @BookingsScope
    @Provides
    fun provideView(context: Context): BookingsView = BookingsViewImpl(context)

    @BookingsScope
    @Provides
    fun provideInteractor(repository: BookingsRepository): BookingsInteractor
            = BookingsInteractorImpl(repository, uuidFactory)

    @BookingsScope
    @Provides
    fun providePresenter(view: BookingsView, interactor: BookingsInteractor, router: BookingsRouter): BookingsPresenter =
            BookingsPresenterImpl(view, interactor, router)

    @BookingsScope
    @Provides
    fun provideRouter(): BookingsRouter = BookingsRouterImpl(activity)

}

@BookingsScope
@Component(modules = [BookingsModule::class], dependencies = [NetworkComponent::class])
interface BookingsComponent {
    fun inject(activity: BookingsActivity)
}