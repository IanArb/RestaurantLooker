package com.ianarbuckle.restaurants.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ianarbuckle.restaurants.RestaurantsProvider
import com.ianarbuckle.restaurants.ui.home.core.presenter.RestaurantsPresenter
import com.ianarbuckle.restaurants.ui.home.core.view.RestaurantsView
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
class RestaurantsFragment : Fragment() {

    @Inject
    lateinit var view: RestaurantsView

    @Inject
    lateinit var presenter: RestaurantsPresenter

    companion object {
        val TAG: String = RestaurantsFragment::class.java.simpleName
        fun newInstance() = RestaurantsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        RestaurantsProvider.get().inject(this)
        return view.getView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }
}

