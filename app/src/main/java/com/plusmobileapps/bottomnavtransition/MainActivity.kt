package com.plusmobileapps.bottomnavtransition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.plusmobileapps.bottomnavtransition.ui.BottomNavFragment
import com.plusmobileapps.bottomnavtransition.ui.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_content, HomeFragment(), HomeFragment::class.java.simpleName)
            .replace(R.id.container_bottom_nav, BottomNavFragment.newInstance(), BottomNavFragment.TAG)
            .commit()
    }
}
