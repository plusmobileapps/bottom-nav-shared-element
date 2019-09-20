package com.plusmobileapps.bottomnavtransition.ui

import android.view.View
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.ChangeTransform
import androidx.transition.TransitionInflater
import androidx.transition.TransitionSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.plusmobileapps.bottomnavtransition.R
import com.plusmobileapps.bottomnavtransition.ui.dashboard.DashboardFragment
import com.plusmobileapps.bottomnavtransition.ui.home.HomeFragment
import com.plusmobileapps.bottomnavtransition.ui.notifications.NotificationsFragment


fun Fragment.setupBottomNav(view: View, selectedId: Int) {
    val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.nav_view).apply {
        //select the id first to prevent the nav selection listener from being stuck infinitely
        selectedItemId = selectedId
        transitionName = getString(R.string.bottom_nav_transition_name)
    }
    //give the ripple a chance to show before replacing
    postponeEnterTransition()
    view.postDelayed({
        startPostponedEnterTransition()
    }, 150)

    bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_dashboard -> DashboardFragment()
            R.id.navigation_home -> HomeFragment()
            R.id.navigation_notifications -> NotificationsFragment()
            else -> null
        }?.let { fragment ->
            fragmentManager?.beginTransaction()?.let { transaction ->
                transaction
                    .setReorderingAllowed(true)
                    .addSharedElement(
                        bottomNavigationView,
                        getString(R.string.bottom_nav_transition_name)
                    )
//                    .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                    .replace(R.id.container, fragment, fragment::class.java.simpleName)
                    .commit()
            }
        } ?: return@setOnNavigationItemSelectedListener false

        return@setOnNavigationItemSelectedListener true
    }
}
