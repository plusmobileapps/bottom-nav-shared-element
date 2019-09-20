package com.plusmobileapps.bottomnavtransition

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.plusmobileapps.bottomnavtransition.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val BOTTOM_NAV_TRANSITION_NAME = "Bottom navigation transition name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment(), HomeFragment::class.java.simpleName)
            .commit()
    }
}
