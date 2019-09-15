package com.ianarbuckle.booking.ui.bookings.builder

import android.content.Context
import com.ianarbuckle.booking.network.builder.NetworkModule
import com.ianarbuckle.booking.network.manager.BookingServiceManager
import com.ianarbuckle.booking.ui.bookings.BookingsFragment
import com.ianarbuckle.booking.ui.bookings.core.interactor.BookingsInteractor
import com.ianarbuckle.booking.ui.bookings.core.interactor.BookingsInteractorImpl
import com.ianarbuckle.booking.ui.bookings.core.presenter.BookingsPresenter
import com.ianarbuckle.booking.ui.bookings.core.presenter.BookingsPresenterImpl
import com.ianarbuckle.booking.ui.bookings.core.repository.BookingsRepository
import com.ianarbuckle.booking.ui.bookings.core.repository.BookingsRepositoryImpl
import com.ianarbuckle.booking.ui.bookings.core.view.BookingsView
import com.ianarbuckle.booking.ui.bookings.core.view.BookingsViewImpl
import com.ianarbuckle.core.utils.DeviceUuidFactory
import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
annotation class BookingScope

@Module
class BookingModule(private val fragment: BookingsFragment, private val uuidFactory: DeviceUuidFactory) {

    @BookingScope
    @Provides
    fun provideContext(): Context = fragment.requireContext()

    @BookingScope
    @Provides
    fun provideView(context: Context): BookingsView = BookingsViewImpl(context)

    @BookingScope
    @Provides
    fun provideInteractor(repository: BookingsRepository): BookingsInteractor
            = BookingsInteractorImpl(repository, uuidFactory)

    @BookingScope
    @Provides
    fun providePresenter(view: BookingsView, interactor: BookingsInteractor): BookingsPresenter =
            BookingsPresenterImpl(view, interactor)

    @BookingScope
    @Provides
    fun provideRepository(serviceManager: BookingServiceManager): BookingsRepository = BookingsRepositoryImpl(serviceManager)

}

@BookingScope
@Component(modules = [BookingModule::class, NetworkModule::class])
interface BookingComponent {
    fun inject(fragment: BookingsFragment)
}