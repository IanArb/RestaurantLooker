package com.ianarbuckle.booking.ui.booking.builder

import android.content.Context
import com.ianarbuckle.booking.BookingNavigator
import com.ianarbuckle.booking.network.builder.NetworkComponent
import com.ianarbuckle.booking.network.repository.BookingsRepository
import com.ianarbuckle.booking.ui.booking.BookingFragment
import com.ianarbuckle.booking.ui.booking.core.interactor.BookingInteractor
import com.ianarbuckle.booking.ui.booking.core.interactor.BookingInteractorImpl
import com.ianarbuckle.booking.ui.booking.core.presenter.BookingPresenter
import com.ianarbuckle.booking.ui.booking.core.presenter.BookingPresenterImpl
import com.ianarbuckle.booking.ui.booking.core.router.BookingRouter
import com.ianarbuckle.booking.ui.booking.core.router.BookingRouterImpl
import com.ianarbuckle.booking.ui.booking.core.view.BookingView
import com.ianarbuckle.booking.ui.booking.core.view.BookingViewImpl
import com.ianarbuckle.core.utils.DeviceUuidFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class BookingScope

@Module
class BookingModule(private val fragment: BookingFragment) {

    @BookingScope
    @Provides
    fun provideContext(): Context = fragment.requireContext()

    @BookingScope
    @Provides
    fun provideBookingView(context: Context): BookingView = BookingViewImpl(context)

    @BookingScope
    @Provides
    fun provideBookingPresenter(view: BookingView, interactor: BookingInteractor, router: BookingRouter): BookingPresenter
            = BookingPresenterImpl(view, interactor, router)

    @BookingScope
    @Provides
    fun provideBookingInteractor(repository: BookingsRepository): BookingInteractor = BookingInteractorImpl(repository)

    @BookingScope
    @Provides
    fun provideBookingRouter(context: Context): BookingRouter = BookingRouterImpl(context)

}

@BookingScope
@Component(modules = [BookingModule::class], dependencies = [NetworkComponent::class])
interface BookingComponent {
    fun inject(fragment: BookingFragment)
}