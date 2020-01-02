package com.example.internz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.internz.R
import com.example.internz.ui.home.MainHomeFragment
import com.example.internz.ui.notification.NotificationFragment
import com.example.internz.ui.profile.main.MainProfileFragment
import com.example.internz.ui.story.StoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class BottomBarActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private var backKeyPressedTime : Long = 0

    // 액티비티가 프래그먼트를 멤버 변수화 해서 관리하겠다. 메모리 단위
    private val fragmentHome = MainHomeFragment()
    private val fragmentNotification = NotificationFragment()
    private val fragmentStory = StoryFragment()
    private val fragmentProfile = MainProfileFragment()

    //  리스트 형식으로 정리
    private val fragments = mapOf(
        R.id.frag_navigation_home to fragmentHome,
        R.id.frag_navigation_notice to fragmentNotification,
        R.id.frag_navigation_story to fragmentStory,
        R.id.frag_navigation_profile to fragmentProfile
    )

    // active는 현재 보여지는 화면
    private lateinit var active: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(this)

        //for each문은 자동으로 처음부터 끝까지 리스트 접근, 그래서 manager가 관리 , 관리하기 위해서는 add로 manager한테 권한주기
        // 그리고 hide로 전부 숨긴다.
        fragments.forEach { (key, frag) ->
            supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, frag, key.toString()).hide(frag).commit()
        }


        // 임의로 4개중에 하나를 화면에 표시한다.
        supportFragmentManager.beginTransaction().show(fragmentHome).commit()
        active = fragmentHome
    }



    // 현재 화면을 숨기고 우리가 선택한 화면을 show한다. 이게 내부적으로 돌아가는 것
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            in fragments.keys -> {
                fragments[menuItem.itemId]?.let {
                    supportFragmentManager.beginTransaction().hide(active).show(it).commit()
                    active = it
                }
            }
            else -> return false
        }
        return true
    }

    //뒤로가기 2번 종료
    override fun onBackPressed() {
        val toast : Toast = Toast.makeText(this, "\'뒤로\'버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT)

        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            toast.show()
        } else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast.cancel()
            finish()
        }
    }


}





