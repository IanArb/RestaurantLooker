package com.ianarbuckle.restaurants.ui.menu.builder

import android.app.Activity
import android.content.Context
import com.ianarbuckle.restaurants.ui.menu.MenuActivity
import com.ianarbuckle.restaurants.ui.menu.core.interactor.DefaultMenuInteractor
import com.ianarbuckle.restaurants.ui.menu.core.interactor.MenuInteractor
import com.ianarbuckle.restaurants.ui.menu.core.presenter.DefaultMenuPresenter
import com.ianarbuckle.restaurants.ui.menu.core.presenter.MenuPresenter
import com.ianarbuckle.restaurants.ui.menu.core.router.DefaultMenuRouter
import com.ianarbuckle.restaurants.ui.menu.core.router.MenuRouter
import com.ianarbuckle.restaurants.ui.menu.core.view.DefaultMenuView
import com.ianarbuckle.restaurants.ui.menu.core.view.MenuView
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

/**
 * Created by Ian Arbuckle on 2019-04-23.
 *
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MenuScope

@Module
class MenuModule(private val activity: MenuActivity) {

    @MenuScope
    @Provides
    fun provideContext(): Context = activity

    @MenuScope
    @Provides
    fun provideInteractor(): MenuInteractor = DefaultMenuInteractor(activity)

    @MenuScope
    @Provides
    fun providePresenter(interactor: MenuInteractor, view: MenuView, router: MenuRouter): MenuPresenter
            = DefaultMenuPresenter(view, interactor, router, activity)

    @MenuScope
    @Provides
    fun provideView(context: Context): MenuView = DefaultMenuView(context)

    @MenuScope
    @Provides
    fun provideRouter(): MenuRouter = DefaultMenuRouter(activity)
}

@MenuScope
@Component(modules = [MenuModule::class])
interface MenuComponent {
    fun inject(activity: MenuActivity)
}