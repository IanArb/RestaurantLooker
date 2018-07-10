package com.ianarbuckle.seathelper.home.core.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by Ian Arbuckle on 28/06/2018.
 *
 */
class ViewPagerAdapter(fragmentManager: FragmentManager?) : FragmentStatePagerAdapter(fragmentManager) {

    private var fragments: MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    fun addFragment(fragment: Fragment) = fragments.add(fragment)
}