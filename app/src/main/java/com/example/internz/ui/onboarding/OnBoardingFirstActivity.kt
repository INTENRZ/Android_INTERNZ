package com.example.internz.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.internz.R
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.internz.R.*


class OnBoardingFirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_on_boarding_first)

        val intent = Intent(this@OnBoardingFirstActivity , OnBoardingSecondActivity::class.java)
        startActivity(intent)
        overridePendingTransition(
            anim.nav_default_enter_anim,
            anim.nav_default_exit_anim)
    }
}