package com.ianarbuckle.restaurants.home.core.presenter

import utils.TestContextProvider
import android.arch.lifecycle.LifecycleOwner
import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.restaurants.home.core.interactor.RestaurantsInteractor
import com.ianarbuckle.restaurants.home.core.view.RestaurantsView
import com.ianarbuckle.restaurants.home.router.RestaurantsRouter
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.test.TestCoroutineContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner
import utils.buildRestaurantMock
import org.mockito.Mockito.`when` as given

/**
 * Created by Ian Arbuckle on 13/08/2018.
 *
 */
@RunWith(MockitoJUnitRunner::class)
class RestaurantsPresenterTest {

    private lateinit var presenter: RestaurantsPresenter

    @Mock
    private lateinit var interactor: RestaurantsInteractor

    @Mock
    private lateinit var view: RestaurantsView

    @Mock
    private lateinit var lifecycleOwner: LifecycleOwner

    @Mock
    private lateinit var router: RestaurantsRouter

    private val context = TestCoroutineContext()

    @Before
    fun setup() {
        initMocks(this)
        presenter = DefaultRestaurantsPresenter(view, interactor, router, lifecycleOwner, TestContextProvider())
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
    }

    @Test
    fun `test that onCreate populates restaurants and verify results is not empty`() = runBlocking(context) {
        given(view.observeOnPullToRefresh()).thenReturn(Observable.never())
        given(view.observeOnTryAgainClick()).thenReturn(Observable.never())
        given(interactor.fetchRestaurants()).thenReturn(buildRestaurantMock())

        presenter.onCreate()

        verify(view, times(1)).showRestaurants(any())
        verify(view, times(1)).showLoading()
        verify(view, times(1)).hideLoading()
        assertThat(interactor.fetchRestaurants()[0].results).isNotEmpty()
    }

    @Test
    fun `test that onCreate triggers pull to refresh`() = runBlocking(context) {
        given(view.observeOnPullToRefresh()).thenReturn(Observable.just(Any()))
        given(view.observeOnTryAgainClick()).thenReturn(Observable.never())
        given(interactor.fetchRestaurants()).thenReturn(buildRestaurantMock())

        presenter.onCreate()

        verify(view, times(2)).showRestaurants(any())
        verify(view, times(3)).hideLoading()
    }

    @Test
    fun `test that onCreate triggers retry when clicked`() = runBlocking(context) {
        given(view.observeOnTryAgainClick()).thenReturn(Observable.just(Any()))
        given(view.observeOnPullToRefresh()).thenReturn(Observable.never())
        given(interactor.fetchRestaurants()).thenReturn(buildRestaurantMock())

        presenter.onCreate()

        verify(view, times(2)).showRestaurants(any())
        verify(view, times(3)).showLoading()
    }

}