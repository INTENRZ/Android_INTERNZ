package com.example.internz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.internz.R
import com.example.internz.ui.notification.NotificationFragment
import com.example.internz.ui.home.MainHomeFragment
import com.example.internz.ui.profile.main.MainProfileFragment
import kotlinx.android.synthetic.main.activity_home.*
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.frag_navigation_home,
                R.id.frag_navigation_notice,
                R.id.frag_navigation_story,
                R.id.frag_navigation_profile
            )
        )
        navView.setupWithNavController(navController)

        MainHelper.setFT(supportFragmentManager.beginTransaction())

    }
}





