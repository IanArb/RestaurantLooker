package com.ianarbuckle.booking.ui.phonePrefix.presenter

import com.ianarbuckle.booking.ui.phonePrefix.core.interactor.PhonePrefixInteractor
import com.ianarbuckle.booking.ui.phonePrefix.core.presenter.PhonePrefixPresenter
import com.ianarbuckle.booking.ui.phonePrefix.core.presenter.PhonePrefixPresenterImpl
import com.ianarbuckle.booking.ui.phonePrefix.core.router.PhonePrefixRouter
import com.ianarbuckle.booking.ui.phonePrefix.core.view.PhonePrefixView
import com.ianarbuckle.models.booking.Country
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

/**
 * Created by Ian Arbuckle on 2019-09-09.
 *
 */
class PhonePrefixPresenterTest {

    @Mock
    lateinit var view: PhonePrefixView

    @Mock
    lateinit var interactor: PhonePrefixInteractor

    @Mock
    lateinit var router: PhonePrefixRouter

    lateinit var presenter: PhonePrefixPresenter

    @Before
    fun setup() {
        initMocks(this)
        presenter = PhonePrefixPresenterImpl(view, interactor, router)
    }

    @Test
    fun `verify that onCreate should show countries`() {
        val countries = ArrayList<Country>()
        val ireland = Country("Ireland", "IE", "353")
        val uk = Country("United Kingdom", "UK", "444")
        countries.apply {
            add(ireland)
            add(uk)
        }

        whenever(interactor.getCountries()).thenReturn(countries)

        presenter.onCreate()

        verify(view, times(1)).showCountries(countries)
    }

    @Test
    fun `verify that onCreate should navigate back on toolbar click`() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<() -> Unit>()
        verify(view).toolbarBackClickListener(clickCaptor.capture())
        clickCaptor.firstValue.invoke()

        verify(router, times(1)).navigateBack()
    }

    @Test
    fun `verify that onCreate should `() {
        presenter.onCreate()

        val clickCaptor = argumentCaptor<(Country) -> Unit>()
        verify(view).countryItemClickListener(clickCaptor.capture())
        clickCaptor.firstValue.invoke(Country("Ireland", "IE", "353"))

        verify(router, times(1)).navigateBackWithPrefix(anyString())
    }
}