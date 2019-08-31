package com.ianarbuckle.booking.ui.reservation.builder

import android.content.Context
import com.ianarbuckle.booking.network.builder.NetworkModule
import com.ianarbuckle.booking.network.manager.BookingServiceManager
import com.ianarbuckle.booking.ui.reservation.ReservationActivity
import com.ianarbuckle.booking.ui.reservation.core.interactor.ReservationInteractor
import com.ianarbuckle.booking.ui.reservation.core.interactor.ReservationInteractorImpl
import com.ianarbuckle.booking.ui.reservation.core.presenter.ReservationPresenter
import com.ianarbuckle.booking.ui.reservation.core.presenter.ReservationPresenterImpl
import com.ianarbuckle.booking.ui.reservation.core.repository.ReservationRepository
import com.ianarbuckle.booking.ui.reservation.core.repository.ReservationRepositoryImpl
import com.ianarbuckle.booking.ui.reservation.core.router.ReservationRouter
import com.ianarbuckle.booking.ui.reservation.core.router.ReservationRouterImpl
import com.ianarbuckle.booking.ui.reservation.core.view.ReservationView
import com.ianarbuckle.booking.ui.reservation.core.view.ReservationViewImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ReservationScope

@Module
class ReservationModule(private val activity: ReservationActivity) {

    @ReservationScope
    @Provides
    fun provideContext(): Context = activity

    @ReservationScope
    @Provides
    fun provideView(context: Context): ReservationView = ReservationViewImpl(context)

    @ReservationScope
    @Provides
    fun provideInteractor(repository: ReservationRepository): ReservationInteractor = ReservationInteractorImpl(repository)

    @ReservationScope
    @Provides
    fun providePresenter(view: ReservationView, interactor: ReservationInteractor, router: ReservationRouter): ReservationPresenter =
            ReservationPresenterImpl(view, interactor, router)

    @ReservationScope
    @Provides
    fun provideRouter(): ReservationRouter = ReservationRouterImpl(activity)

    @ReservationScope
    @Provides
    fun provideRepository(serviceManager: BookingServiceManager): ReservationRepository = ReservationRepositoryImpl(serviceManager)
}

@ReservationScope
@Component(modules = [NetworkModule::class, ReservationModule::class])
interface ReservationComponent {
    fun inject(activity: ReservationActivity)
}