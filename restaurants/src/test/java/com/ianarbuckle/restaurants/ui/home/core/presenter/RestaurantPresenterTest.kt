package com.ianarbuckle.restaurants.ui.home.core.presenter

import androidx.lifecycle.LifecycleOwner
import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.restaurants.data.Restaurant
import com.ianarbuckle.restaurants.ui.home.core.interactor.RestaurantsInteractor
import com.ianarbuckle.restaurants.ui.home.core.view.RestaurantsView
import com.ianarbuckle.restaurants.ui.home.router.RestaurantsRouter
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner
import utils.buildRestaurantMock
import utils.createRestaurant
import org.mockito.Mockito.`when` as given

/**
 * Created by Ian Arbuckle on 13/08/2018.
 *
 */
@RunWith(MockitoJUnitRunner::class)
class RestaurantPresenterTest {

    private lateinit var presenter: RestaurantsPresenter

    @Mock
    private lateinit var interactor: RestaurantsInteractor

    @Mock
    private lateinit var view: RestaurantsView

    @Mock
    private lateinit var router: RestaurantsRouter

    @Mock
    private lateinit var lifecycleOwner: LifecycleOwner

    private val testDispatcher = TestCoroutineDispatcher()

    private val testScope = TestCoroutineScope(testDispatcher)

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.IO)
        initMocks(this)
        presenter = DefaultRestaurantsPresenter(view, interactor, router, lifecycleOwner)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `verify that onCreate it should populate restaurants and that results are not empty`() {
        runBlocking {
            launch {
                given(interactor.fetchRestaurants()).thenReturn(buildRestaurantMock())
                presenter.onCreate()

                withContext(Dispatchers.Main) {
                    verify(view, times(1)).showRestaurants(buildRestaurantMock())
                }
                verify(view, times(1)).showLoading()
                verify(view, times(1)).hideLoading()
                assertThat(interactor.fetchRestaurants()).isNotEmpty()
            }
        }
    }


}