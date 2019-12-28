package com.example.internz.ui

import StoryFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.internz.R
import com.example.internz.ui.story.DetailStoryFragment
import com.example.internz.ui.notification.NotificationFragment
import com.example.internz.ui.home.HomeFragment
import com.example.internz.ui.profile.main.MainProfileFragment
import kotlinx.android.synthetic.main.activity_home.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        // 프래그먼트들 선언
        val fragmentHome = HomeFragment()
        val fragmentNotification = NotificationFragment()
        val fragmentStory = StoryFragment()
        val fragmentProfile = MainProfileFragment()

        /* 프래그먼트 교체 관련 */
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, fragmentHome).commit()

        MainHelper.setFT(supportFragmentManager.beginTransaction())

        // 하단 탭 클릭 리스너 (클릭 시 프래그먼트 교체됨)
        img_maintab_home.setOnClickListener {
            img_maintab_notice.isSelected = false
            img_maintab_story.isSelected = false
            img_maintab_profile.isSelected = false
            if(img_maintab_home.isSelected == false){
                img_maintab_home.isSelected = true
            }else{
                img_maintab_home.isSelected = false
            }

            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragmentHome)
            fragmentTransaction.commit()

        }
        img_maintab_notice.setOnClickListener {
            img_maintab_home.isSelected = false
            img_maintab_story.isSelected = false
            img_maintab_profile.isSelected = false
            if(img_maintab_notice.isSelected == false){
                img_maintab_notice.isSelected = true
            }else{
                img_maintab_notice.isSelected = false
            }

            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragmentNotification)
            fragmentTransaction.commit()
        }
        img_maintab_story.setOnClickListener {
            img_maintab_notice.isSelected = false
            img_maintab_home.isSelected = false
            img_maintab_profile.isSelected = false
            if(img_maintab_story.isSelected == false){
                img_maintab_story.isSelected = true
            }else{
                img_maintab_story.isSelected = false
            }

            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, DetailStoryFragment())
            fragmentTransaction.commit()
        }
        img_maintab_profile.setOnClickListener {
            img_maintab_notice.isSelected = false
            img_maintab_story.isSelected = false
            img_maintab_home.isSelected = false
            if(img_maintab_profile.isSelected == false){
                img_maintab_profile.isSelected = true
            }else{
                img_maintab_profile.isSelected = false
            }

            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragmentProfile)
            fragmentTransaction.commit()
        }
    }


}



