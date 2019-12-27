package com.example.internz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.example.internz.R
import com.example.internz.feature.notification.NotificationFragment
import com.example.internz.ui.Story.StoryFragment
import com.example.internz.ui.home.HomeFragment
import com.example.internz.ui.notice.NoticeFragment
import com.example.internz.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentHome = HomeFragment()
        val fragmentNotification = NotificationFragment()

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.container, fragmentHome).commit()
        img_maintab_home.setOnClickListener {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragmentHome)
            fragmentTransaction.commit()
        }
        img_maintab_notice.setOnClickListener {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragmentNotification)
            fragmentTransaction.commit()
        }
        img_maintab_notice.setOnClickListener {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragmentNotification)
            fragmentTransaction.commit()
        }
        img_maintab_notice.setOnClickListener {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragmentNotification)
            fragmentTransaction.commit()
        }
    }
}



