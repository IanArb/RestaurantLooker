package com.ianarbuckle.seathelper.home.router

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ianarbuckle.seathelper.R
import com.ianarbuckle.seathelper.utils.BottomNavigationPosition
import com.ianarbuckle.seathelper.utils.createFragment
import com.ianarbuckle.seathelper.utils.getTag

/**
 * Created by Ian Arbuckle on 18/05/2018.
 *
 */
class DefaultHomeRouter(private val supportFragmentManager: FragmentManager) : HomeRouter {

    override fun switchFragment(navigationPosition: BottomNavigationPosition): Boolean {
        val fragment = supportFragmentManager.findFragment(navigationPosition)
        if (fragment.isAdded) return false
        detachFragment()
        attachFragment(fragment, navigationPosition.getTag())
        supportFragmentManager.executePendingTransactions()
        return true
    }

    private fun FragmentManager.findFragment(position: BottomNavigationPosition): Fragment {
        return findFragmentByTag(position.getTag()) ?: position.createFragment()
    }

    private fun detachFragment() {
        supportFragmentManager.findFragmentById(R.id.container)?.also {
            supportFragmentManager.beginTransaction().detach(it).commit()
        }
    }

    private fun attachFragment(fragment: Fragment, tag: String) {
        if (fragment.isDetached) {
            supportFragmentManager.beginTransaction().attach(fragment).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.container, fragment, tag).commit()
        }

        supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

}