package com.ianarbuckle.restaurants.home

import androidx.lifecycle.LifecycleObserver
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ianarbuckle.restaurants.RestaurantsProvider
import com.ianarbuckle.restaurants.home.core.presenter.RestaurantsPresenter
import com.ianarbuckle.restaurants.home.core.view.RestaurantsView
import javax.inject.Inject

/**
 * Created by Ian Arbuckle on 11/07/2018.
 *
 */
class RestaurantsFragment : Fragment(), LifecycleObserver {

    @Inject lateinit var view: RestaurantsView

    @Inject lateinit var presenter: RestaurantsPresenter

    companion object {
        val TAG: String = RestaurantsFragment::class.java.simpleName
        fun newInstance() = RestaurantsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RestaurantsProvider.get().inject(fragment = this)
        presenter.addLifecycleObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return view.getView()
    }
}

