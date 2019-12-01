package com.ianarbuckle.booking.ui.reservation.builder

import android.content.Context
import com.ianarbuckle.booking.BookingNavigator
import com.ianarbuckle.booking.network.builder.NetworkComponent
import com.ianarbuckle.booking.network.builder.NetworkModule
import com.ianarbuckle.booking.network.manager.BookingServiceManager
import com.ianarbuckle.booking.network.repository.BookingsRepository
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
import com.ianarbuckle.core.utils.DeviceUuidFactory
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
class ReservationModule(private val activity: ReservationActivity, private val navigator: BookingNavigator,
                        private val country: String, private val uuidFactory: DeviceUuidFactory) {

    @ReservationScope
    @Provides
    fun provideContext(): Context = activity

    @ReservationScope
    @Provides
    fun provideView(context: Context): ReservationView = ReservationViewImpl(context)

    @ReservationScope
    @Provides
    fun provideInteractor(repository: BookingsRepository): ReservationInteractor
            = ReservationInteractorImpl(activity, repository, country, uuidFactory)

    @ReservationScope
    @Provides
    fun providePresenter(view: ReservationView, interactor: ReservationInteractor, router: ReservationRouter): ReservationPresenter =
            ReservationPresenterImpl(view, interactor, router)

    @ReservationScope
    @Provides
    fun provideRouter(): ReservationRouter = ReservationRouterImpl(activity, navigator)
}

@ReservationScope
@Component(modules =  [ReservationModule::class], dependencies = [NetworkComponent::class])
interface ReservationComponent {
    fun inject(activity: ReservationActivity)
}