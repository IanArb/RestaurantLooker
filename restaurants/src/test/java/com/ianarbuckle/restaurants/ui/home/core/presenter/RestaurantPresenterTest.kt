package com.ianarbuckle.restaurants.ui.home.core.presenter

import androidx.lifecycle.LifecycleOwner
import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.restaurants.ui.home.core.interactor.RestaurantsInteractor
import com.ianarbuckle.restaurants.ui.home.core.view.RestaurantsView
import com.ianarbuckle.restaurants.ui.home.router.RestaurantsRouter
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner
import utils.ModelData
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

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.IO)
        initMocks(this)
        presenter = DefaultRestaurantsPresenter(view, interactor, router)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `verify that onCreate it should populate restaurants and that results are not empty`() {
        runBlocking {
            launch {
                given(interactor.fetchRestaurants()).thenReturn(ModelData.buildRestaurantsModel())
                presenter.onCreate()

                withContext(Dispatchers.Main) {
                    verify(view, times(1)).showRestaurants(ModelData.buildRestaurantsModel())
                }
                verify(view, times(1)).showLoading()
                verify(view, times(1)).hideLoading()
                assertThat(interactor.fetchRestaurants()).isNotEmpty()
            }
        }
    }


}