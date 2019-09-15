package com.ianarbuckle.seathelper.home.core.presenter

import androidx.lifecycle.LifecycleOwner
import android.view.MenuItem
import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.seathelper.utils.BottomNavigationPosition
import com.ianarbuckle.seathelper.home.core.view.HomeView
import com.ianarbuckle.seathelper.home.router.HomeRouter
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by Ian Arbuckle on 05/07/2018.
 *
 */
@RunWith(MockitoJUnitRunner::class)
class RestaurantPresenterTest {

    private lateinit var presenter: HomePresenter

    @Mock
    private lateinit var view: HomeView

    @Mock
    private lateinit var router: HomeRouter

    @Mock
    private lateinit var lifecycleOwner: LifecycleOwner

    @Before
    fun setup() {
        initMocks(this)
        presenter = DefaultHomePresenter(view, router)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
    }

    @Test
    fun `test onCreate should call switch fragment`() {
        val navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

        whenever(view.observeNavigationItemSelected()).thenReturn(Observable.just(mock(MenuItem::class.java)))

        presenter.onCreate()

        verify(view, times(1)).observeNavigationItemSelected()
        verify(router, times(1)).switchFragment(navPosition)
        assertThat(navPosition).isEqualTo(BottomNavigationPosition.HOME)
    }
}