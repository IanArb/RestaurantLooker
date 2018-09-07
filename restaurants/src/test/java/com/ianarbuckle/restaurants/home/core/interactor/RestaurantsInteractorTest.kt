package com.ianarbuckle.restaurants.home.core.interactor

import com.ianarbuckle.restaurants.home.model.Restaurant
import com.ianarbuckle.restaurants.network.repository.RestaurantsRepository
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as given
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Ian Arbuckle on 13/08/2018.
 *
 */
@RunWith(MockitoJUnitRunner::class)
class RestaurantsInteractorTest {

    private lateinit var interactor: RestaurantsInteractor

    @Mock
    private lateinit var repository: RestaurantsRepository

    @Mock
    private lateinit var deferred: Deferred<MutableList<Restaurant>>

    @Before
    fun setup() {
        initMocks(this)
        interactor = DefaultRestaurantsInteractor(repository)
    }

    @Test
    fun `test that repository with restaurants gets called`() {
        runBlocking {
            given(repository.fetchRestaurants()).thenReturn(deferred)

            interactor.fetchRestaurants()

            verify(repository, times(1)).fetchRestaurants()
        }
    }
}