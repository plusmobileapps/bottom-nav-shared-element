package com.plusmobileapps.bottomnavtransition.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.plusmobileapps.bottomnavtransition.R

class BottomNavFragment private constructor(): Fragment() {

    companion object {

        val TAG = BottomNavFragment::class.java.simpleName

        fun newInstance(): BottomNavFragment {
            return BottomNavFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_nav, container, false)
        val bottomNav = view.findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_dashboard -> DashboardFragment()
                R.id.navigation_home -> HomeFragment()
                R.id.navigation_notifications -> NotificationsFragment()
                else -> null
            }?.let { fragment ->
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.container_content, fragment, fragment::class.java.simpleName)
                    ?.commit()
            } ?: return@setOnNavigationItemSelectedListener false

            return@setOnNavigationItemSelectedListener true
        }
        return view
    }

}