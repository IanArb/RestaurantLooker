package com.ianarbuckle.restaurants.ui.menu.core.presenter

import com.ianarbuckle.restaurants.ui.menu.MenuActivity
import com.ianarbuckle.restaurants.ui.menu.core.interactor.MenuInteractor
import com.ianarbuckle.restaurants.ui.menu.core.router.MenuRouter
import com.ianarbuckle.restaurants.ui.menu.core.view.MenuView
import com.nhaarman.mockitokotlin2.argumentCaptor
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever
import org.mockito.MockitoAnnotations.initMocks
import utils.createLunchMenu

/**
 * Created by Ian Arbuckle on 2019-05-11.
 *
 */
class MenuPresenterTest {

    @Mock
    lateinit var interactor: MenuInteractor

    @Mock
    lateinit var view: MenuView

    @Mock
    lateinit var router: MenuRouter

    @Mock
    lateinit var activity: MenuActivity

    lateinit var presenter: MenuPresenter

    @Before
    fun setup() {
        initMocks(this)
        presenter = DefaultMenuPresenter(view, interactor, router, activity)
    }

    @Test
    fun `verify that toolbar title is populated and displayed`() {
        whenever(interactor.getRestaurantName()).thenReturn("Crillios")

        presenter.onCreate()

        verify(view, times(1)).showToolbarTitle(anyString())
        verify(interactor, times(1)).getRestaurantName()
    }

    @Test
    fun `verify that image url banner is populated and displayed`() {
        whenever(interactor.getImageBannerUrl()).thenReturn("imageurl")

        presenter.onCreate()

        verify(view, times(1)).showImageBanner(anyString())
        verify(interactor, times(1)).getImageBannerUrl()
    }

    @Test
    fun `verify that menu is populated and displayed`() {
        whenever(interactor.getMenu()).thenReturn(createLunchMenu())

        presenter.onCreate()

        verify(view, times(1)).showMenu(anyList())
        verify(interactor, times(1)).getMenu()
    }

    @Test
    fun `verify that when toolbar back icon is clicked it should navigate to home`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).toolbarClickListener(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        verify(router, times(1)).navigateBack()
    }
}