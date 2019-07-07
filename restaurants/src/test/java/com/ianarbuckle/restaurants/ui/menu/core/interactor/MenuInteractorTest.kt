package com.ianarbuckle.restaurants.ui.menu.core.interactor

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import com.google.common.truth.Truth.assertThat
import com.ianarbuckle.restaurants.data.Dish
import com.ianarbuckle.restaurants.data.Price
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever
import org.mockito.MockitoAnnotations.initMocks
import utils.createRestaurant

/**
 * Created by Ian Arbuckle on 2019-05-11.
 *
 */
class MenuInteractorTest {

    @Mock
    lateinit var activity: Activity

    @Mock
    lateinit var intent: Intent

    lateinit var interactor: MenuInteractor

    @Before
    fun setup() {
        initMocks(this)

        interactor = DefaultMenuInteractor(activity)

        whenever(activity.intent).thenReturn(intent)
    }

    @Test
    fun `verify that when retrieving menu via intent should not be empty or null`() {
        val dish = Dish("lunch", "Codfish", "lunch", Price("EUR", 0f))

        whenever(intent.getParcelableExtra<Parcelable>("menu")).thenReturn(dish)

        assertThat(interactor.getMenu()).isNotNull()
    }

    @Test
    fun `verify that when retrieving restaurant name via intent should not be empty or null`() {
        val restaurant = createRestaurant()

        whenever(intent.getParcelableExtra<Parcelable>("restaurant")).thenReturn(restaurant)

        assertThat(interactor.getRestaurantName()).isNotEmpty()
    }

    @Test
    fun `verify that when retrieving restaurant banner image url should not be empty or null`() {
        val restaurant = createRestaurant()

        whenever(intent.getParcelableExtra<Parcelable>("restaurant")).thenReturn(restaurant)

        assertThat(interactor.getImageBannerUrl()).isNotEmpty()
    }

}