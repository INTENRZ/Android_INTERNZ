package com.example.internz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.internz.R
import com.example.internz.ui.home.MainHomeFragment
import com.example.internz.ui.notification.NotificationFragment
import com.example.internz.ui.profile.main.MainProfileFragment
import com.example.internz.ui.story.StoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val fragmentProfile = MainProfileFragment()
    private val fragmentHome = MainHomeFragment()
    private val fragmentNotification = NotificationFragment()
    private val fragmentStory = StoryFragment()

    private val fragments = mapOf(
        R.id.frag_navigation_home to fragmentHome,
        R.id.frag_navigation_notice to fragmentNotification,
        R.id.frag_navigation_profile to fragmentProfile,
        R.id.frag_navigation_story to fragmentStory

    )

    private lateinit var active: Fragment

    /* 하단 탭 이동시 기존에 보던 프래그먼트 상태 유지 */
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            in fragments.keys -> {
                fragments[menuItem.itemId]?.let {
                    supportFragmentManager.beginTransaction().hide(active).show(it).commit()
                    supportFragmentManager.popBackStack(R.id.nav_host_fragment, menuItem.itemId)
                    active = it
                }
            }
            else -> return false
        }
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)

        fragments.forEach { (key, frag) ->
            supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, frag, key.toString()).hide(frag).commit()
        }

        supportFragmentManager.beginTransaction().show(fragmentHome).commit()
        active = fragmentHome


        navView.setOnNavigationItemSelectedListener(this)

    }



}





