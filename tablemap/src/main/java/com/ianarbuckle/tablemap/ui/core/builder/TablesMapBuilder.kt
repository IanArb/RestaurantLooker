package com.ianarbuckle.tablemap.ui.core.builder

import android.app.Activity
import android.content.Context
import com.ianarbuckle.tablemap.network.builder.NetworkModule
import com.ianarbuckle.tablemap.network.manager.ServiceManager
import com.ianarbuckle.tablemap.ui.TablesMapActivity
import com.ianarbuckle.tablemap.ui.core.interactor.TablesMapInteractor
import com.ianarbuckle.tablemap.ui.core.interactor.TablesMapInteractorImpl
import com.ianarbuckle.tablemap.ui.core.presenter.TablesMapPresenter
import com.ianarbuckle.tablemap.ui.core.presenter.TablesMapPresenterImpl
import com.ianarbuckle.tablemap.ui.core.repository.TablesMapRepository
import com.ianarbuckle.tablemap.ui.core.repository.TablesMapRepositoryImpl
import com.ianarbuckle.tablemap.ui.core.view.TablesMapView
import com.ianarbuckle.tablemap.ui.core.view.TablesMapViewImpl
import com.ianarbuckle.tablemap.ui.router.TablesMapRouter
import com.ianarbuckle.tablemap.ui.router.TablesMapRouterImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

/**
 * Created by Ian Arbuckle on 2019-07-31.
 *
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class TablesMapScope

@Module
class TablesMapModule(private val activity: Activity) {

    @TablesMapScope
    @Provides
    fun provideContext(): Context = activity

    @TablesMapScope
    @Provides
    fun provideView(context: Context): TablesMapView = TablesMapViewImpl(context)

    @TablesMapScope
    @Provides
    fun provideInteractor(repository: TablesMapRepository): TablesMapInteractor = TablesMapInteractorImpl(repository, activity)

    @TablesMapScope
    @Provides
    fun providePresenter(view: TablesMapView, interactor: TablesMapInteractor, router: TablesMapRouter): TablesMapPresenter = TablesMapPresenterImpl(view, interactor, router)

    @TablesMapScope
    @Provides
    fun provideRouter(): TablesMapRouter = TablesMapRouterImpl(activity)

    @TablesMapScope
    @Provides
    fun provideRepository(serviceManager: ServiceManager): TablesMapRepository = TablesMapRepositoryImpl(serviceManager)
}

@TablesMapScope
@Component(modules = [TablesMapModule::class, NetworkModule::class])
interface TablesMapComponent {
    fun inject(activity: TablesMapActivity)
}