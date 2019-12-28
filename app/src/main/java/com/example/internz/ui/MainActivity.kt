package com.example.internz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.internz.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.frag_navigation_home, R.id.frag_navigation_notice, R.id.frag_navigation_story, R.id.frag_navigation_profile
            )
        )
      //  setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        val fragmentHome = MainHomeFragment()
//        val fragmentNotification = NotificationFragment()
//        val fragmentStory = StoryFragment()
//        val fragmentProfile = MainProfileFragment()
//
//        /* 프래그먼트 교체 관련 */
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.add(R.id.container, fragmentHome).commit()
//
//        MainHelper.setFT(supportFragmentManager.beginTransaction())

//        // 하단 탭 클릭 리스너 (클릭 시 프래그먼트 교체됨)
////        img_maintab_home.setOnClickListener {
////            img_maintab_notice.isSelected = false
////            img_maintab_story.isSelected = false
////            img_maintab_profile.isSelected = false
////            if(img_maintab_home.isSelected == false){
////                img_maintab_home.isSelected = true
////            }else{
////                img_maintab_home.isSelected = false
////            }
////
////            val fragmentTransaction = fragmentManager.beginTransaction()
////            fragmentTransaction.replace(R.id.container, fragmentHome)
////            fragmentTransaction.commit()
////
////        }
////        img_maintab_notice.setOnClickListener {
////            img_maintab_home.isSelected = false
////            img_maintab_story.isSelected = false
////            img_maintab_profile.isSelected = false
////            if(img_maintab_notice.isSelected == false){
////                img_maintab_notice.isSelected = true
////            }else{
////                img_maintab_notice.isSelected = false
////            }
////
////            val fragmentTransaction = fragmentManager.beginTransaction()
////            fragmentTransaction.replace(R.id.container, fragmentNotification)
////            fragmentTransaction.commit()
////        }
////        img_maintab_story.setOnClickListener {
////            img_maintab_notice.isSelected = false
////            img_maintab_home.isSelected = false
////            img_maintab_profile.isSelected = false
////            if(img_maintab_story.isSelected == false){
////                img_maintab_story.isSelected = true
////            }else{
////                img_maintab_story.isSelected = false
////            }
////
////            val fragmentTransaction = fragmentManager.beginTransaction()
////            fragmentTransaction.replace(R.id.container, DetailStoryFragment())
////            fragmentTransaction.commit()
////        }
////        img_maintab_profile.setOnClickListener {
////            img_maintab_notice.isSelected = false
////            img_maintab_story.isSelected = false
////            img_maintab_home.isSelected = false
////            if(img_maintab_profile.isSelected == false){
////                img_maintab_profile.isSelected = true
////            }else{
////                img_maintab_profile.isSelected = false
////            }

//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.container, fragmentProfile)
//            fragmentTransaction.commit()
        }
    }






