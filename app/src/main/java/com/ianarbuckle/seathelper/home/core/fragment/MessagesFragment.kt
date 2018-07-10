package com.ianarbuckle.seathelper.home.core.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ianarbuckle.seathelper.R

/**
 * Created by Ian Arbuckle on 30/06/2018.
 *
 */
class MessagesFragment : Fragment() {

    companion object {
        val TAG: String = MessagesFragment::class.java.simpleName
        fun newInstance() = MessagesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.messages_fragment, container, false)
    }
}