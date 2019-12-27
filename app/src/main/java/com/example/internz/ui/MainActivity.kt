package com.example.internz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.internz.R
import com.example.internz.ui.notification.NotificationFragment
import com.example.internz.ui.story.StoryFragment
import com.example.internz.ui.home.HomeFragment
import com.example.internz.ui.profile.TimelineFragment
import kotlinx.android.synthetic.main.activity_home.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 프래그먼트들 선언
        val fragmentHome = HomeFragment()
        val fragmentNotification = NotificationFragment()
        val fragmentStory = StoryFragment()
        val fragmentProfile = TimelineFragment()

        /* 프래그먼트 교체 관련 */
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, fragmentHome).commit()

        // 하단 탭 클릭 리스너 (클릭 시 프래그먼트 교체됨)
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
        img_maintab_story.setOnClickListener {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragmentStory)
            fragmentTransaction.commit()
        }
        img_maintab_profile.setOnClickListener {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragmentProfile)
            fragmentTransaction.commit()
        }
    }
}



