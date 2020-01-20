package com.ianarbuckle.seathelper.splash.builder

import android.app.Activity
import android.content.Context
import com.ianarbuckle.client.AuthenticationClient
import com.ianarbuckle.seathelper.app.builder.AppComponent
import com.ianarbuckle.seathelper.splash.SplashActivity
import com.ianarbuckle.seathelper.splash.core.interactor.SplashInteractorImpl
import com.ianarbuckle.seathelper.splash.core.interactor.SplashInteractor
import com.ianarbuckle.seathelper.splash.core.presenter.SplashPresenterImpl
import com.ianarbuckle.seathelper.splash.core.presenter.SplashPresenter
import com.ianarbuckle.seathelper.splash.core.router.SplashRouter
import com.ianarbuckle.seathelper.splash.core.router.SplashRouterImpl
import com.ianarbuckle.seathelper.splash.core.view.SplashViewImpl
import com.ianarbuckle.seathelper.splash.core.view.SplashView
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SplashScope

@Module
class SplashModule(private val activity: Activity) {

    @SplashScope
    @Provides
    fun provideContext(): Context = activity

    @SplashScope
    @Provides
    fun provideView(context: Context): SplashView = SplashViewImpl(context)

    @SplashScope
    @Provides
    fun provideInteractor(authenticationClient: AuthenticationClient): SplashInteractor = SplashInteractorImpl(authenticationClient)

    @SplashScope
    @Provides
    fun providePresenter(view: SplashView, interactor: SplashInteractor, router: SplashRouter): SplashPresenter = SplashPresenterImpl(view, interactor, router)

    @SplashScope
    @Provides
    fun provideRouter(): SplashRouter = SplashRouterImpl(activity)
}

@SplashScope
@Component(modules = [SplashModule::class], dependencies = [AppComponent::class])
interface SplashComponent {
    fun inject(activity: SplashActivity)
}