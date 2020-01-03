package com.example.internz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import com.example.internz.R
import com.example.internz.feature.homecustomnotification.CustomNotificationAdapter
import com.example.internz.feature.onboarding.OnBoardingAdapter
import com.example.internz.feature.onboarding.OnBoardingFirstFragment
import com.example.internz.feature.onboarding.OnBoardingSecondFragment
import com.example.internz.feature.onboarding.OnBoardingThirdFragment
import kotlinx.android.synthetic.main.activity_onboarding.*


class OnBoardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val onboardAdapter = OnBoardingAdapter(supportFragmentManager)

        onboardAdapter.addFragments(OnBoardingFirstFragment())
        onboardAdapter.addFragments(OnBoardingSecondFragment())
        onboardAdapter.addFragments(OnBoardingThirdFragment())

        viewpager2.adapter = onboardAdapter
        tablayout2.setupWithViewPager(viewpager2)

        //인디케이터
        onBoardingIndicator.setViewPager(viewpager2)
    }
}