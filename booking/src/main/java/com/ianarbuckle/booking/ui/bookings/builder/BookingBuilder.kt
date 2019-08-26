package com.ianarbuckle.booking.ui.bookings.builder

import android.content.Context
import com.ianarbuckle.booking.network.builder.NetworkModule
import com.ianarbuckle.booking.ui.bookings.BookingFragment
import com.ianarbuckle.booking.ui.bookings.core.interactor.BookingInteractor
import com.ianarbuckle.booking.ui.bookings.core.interactor.BookingInteractorImpl
import com.ianarbuckle.booking.ui.bookings.core.repository.BookingRepository
import com.ianarbuckle.booking.ui.bookings.core.repository.BookingRepositoryImpl
import com.ianarbuckle.booking.ui.bookings.router.BookingRouter
import com.ianarbuckle.booking.ui.bookings.router.BookingRouterImpl
import com.ianarbuckle.booking.ui.bookings.core.view.BookingView
import com.ianarbuckle.booking.ui.bookings.core.view.BookingViewImpl
import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Created by Ian Arbuckle on 2019-07-07.
 *
 */
annotation class BookingScope

@Module
class BookingModule(private val fragment: BookingFragment) {

    @BookingScope
    @Provides
    fun provideContext(): Context = fragment.requireContext()

    @BookingScope
    @Provides
    fun provideView(context: Context): BookingView = BookingViewImpl(context)

    @BookingScope
    @Provides
    fun provideInteractor(repository: BookingRepository): BookingInteractor
            = BookingInteractorImpl(repository)

    @BookingScope
    @Provides
    fun provideRepository(): BookingRepository = BookingRepositoryImpl()

    @BookingScope
    @Provides
    fun provideRouter(): BookingRouter = BookingRouterImpl(fragment)

}

@BookingScope
@Component(modules = [BookingModule::class, NetworkModule::class])
interface BookingComponent {
    fun inject(fragment: BookingFragment)
}