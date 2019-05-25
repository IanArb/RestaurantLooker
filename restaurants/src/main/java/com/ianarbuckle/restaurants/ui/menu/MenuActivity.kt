package com.ianarbuckle.restaurants.ui.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import com.ianarbuckle.restaurants.ui.menu.builder.DaggerMenuComponent
import com.ianarbuckle.restaurants.ui.menu.builder.MenuModule
import com.ianarbuckle.restaurants.ui.menu.core.presenter.MenuPresenter
import com.ianarbuckle.restaurants.ui.menu.core.view.MenuView
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 2019-04-23.
 *
 */
class MenuActivity : AppCompatActivity(), LifecycleObserver {

    @Inject
    lateinit var view: MenuView

    @Inject
    lateinit var presenter: MenuPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMenuComponent.builder()
                .menuModule(MenuModule(this))
                .build()
                .inject(this)

        presenter.addLifecycleObserver()

        setContentView(view.getView())
    }

}