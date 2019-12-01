package com.ianarbuckle.restaurants.ui.home.core.interactor

import com.ianarbuckle.restaurants.ui.home.core.repository.RestaurantsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as given
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner
import utils.ModelData


/**
 * Created by Ian Arbuckle on 13/08/2018.
 *
 */
@RunWith(MockitoJUnitRunner::class)
class RestaurantInteractorTest {

    private lateinit var interactor: RestaurantsInteractor

    @Mock
    private lateinit var repository: RestaurantsRepository

    @Before
    fun setup() {
        initMocks(this)
        interactor = DefaultRestaurantsInteractor(repository)
    }

    @Test
    fun `test that repository with restaurants gets called`() {
        runBlocking {
            given(repository.fetchRestaurants()).thenReturn(ModelData.buildRestaurantsModel())

            interactor.fetchRestaurants()

            verify(repository, times(1)).fetchRestaurants()
        }
    }
}