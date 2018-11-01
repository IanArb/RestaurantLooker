package com.ianarbuckle.restaurants.home.core.presenter

import androidx.lifecycle.LifecycleOwner
import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.restaurants.home.core.interactor.RestaurantsInteractor
import com.ianarbuckle.restaurants.home.core.view.RestaurantsView
import com.ianarbuckle.restaurants.home.router.RestaurantsRouter
import com.ianarbuckle.restaurants.utils.RestaurantsBuddyDispatchers
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.test.TestCoroutineContext
import org.junit.After
import org.junit.Before
import org.junit.Rule
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
import kotlinx.coroutines.experimental.android.Main as mainLooperContext

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

    @Mock
    private lateinit var dispatchers: RestaurantsBuddyDispatchers

    private lateinit var testCoroutineContext: TestCoroutineContext

    @Before
    fun setup() {
        initMocks(this)
        testCoroutineContext = TestCoroutineContext()
        presenter = DefaultRestaurantsPresenter(view, interactor, router, dispatchers, lifecycleOwner)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
    }

    @Test
    fun `test that onCreate populates restaurants and verify results is not empty`() {
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, null) {
            withContext(Dispatchers.Main) {
                given(view.observeOnPullToRefresh()).thenReturn(Observable.never())
                given(view.observeOnTryAgainClick()).thenReturn(Observable.never())
                given(interactor.fetchRestaurants()).thenReturn(buildRestaurantMock())

                presenter.onCreate()

                verify(view, times(1)).showRestaurants(any())
                verify(view, times(1)).showLoading()
                verify(view, times(1)).hideLoading()
                assertThat(interactor.fetchRestaurants()[0].results).isNotEmpty()
            }
        }
    }

    @Test
    fun `test that onCreate triggers pull to refresh`() {
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, null) {
            given(view.observeOnPullToRefresh()).thenReturn(Observable.just(Unit))
            given(view.observeOnTryAgainClick()).thenReturn(Observable.never())
            given(interactor.fetchRestaurants()).thenReturn(buildRestaurantMock())

            presenter.onCreate()

            verify(view, times(2)).showRestaurants(any())
            verify(view, times(3)).hideLoading()
        }
    }

    @Test
    fun `test that onCreate triggers retry when clicked`() {
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, null) {
            given(view.observeOnTryAgainClick()).thenReturn(Observable.just(Unit))
            given(view.observeOnPullToRefresh()).thenReturn(Observable.never())
            given(interactor.fetchRestaurants()).thenReturn(buildRestaurantMock())

            presenter.onCreate()

            verify(view, times(2)).showRestaurants(any())
            verify(view, times(3)).showLoading()
        }
    }

    @Test
    fun `test that onCreate should show error when network call throws 404`() {
        GlobalScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT, null) {
            given(view.observeOnTryAgainClick()).thenReturn(Observable.never())
            given(view.observeOnPullToRefresh()).thenReturn(Observable.never())

            presenter.onCreate()

            verify(view, times(1)).showErrorState()
            verify(view, times(1)).hideLoading()

        }
    }
}