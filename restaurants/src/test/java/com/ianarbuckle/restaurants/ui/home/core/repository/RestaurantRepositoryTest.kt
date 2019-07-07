package com.ianarbuckle.restaurants.ui.home.core.repository

import com.ianarbuckle.restaurants.db.dao.RestaurantDAO
import com.ianarbuckle.restaurants.network.RestaurantsService
import com.ianarbuckle.restaurants.network.manager.RestaurantsServiceManager
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.Mockito.`when` as given

/**
 * Created by Ian Arbuckle on 13/08/2018.
 *
 */
class RestaurantRepositoryTest {

    private lateinit var repository: RestaurantsRepository

    @Mock
    private lateinit var manager: RestaurantsServiceManager

    @Mock
    private lateinit var service: RestaurantsService

    @Mock
    private lateinit var dao: RestaurantDAO

    @Before
    fun setup() {
        initMocks(this)
        repository = DefaultRestaurantsRepository(manager, dao)
    }

    @Test
    fun `test that manager returns service`() {
        runBlocking {
            given(manager.getService()).thenReturn(service)

            repository.fetchRestaurants()

            verify(manager, times(1)).getService()
        }
    }
}