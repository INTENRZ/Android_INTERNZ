package com.example.internz.feature.onboarding


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.internz.R
import com.example.internz.feature.signin.SignInActivity
import kotlinx.android.synthetic.main.fragment_on_boardind_third.*

/**
 * A simple [Fragment] subclass.
 */
class OnBoardingThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boardind_third, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnOnBoarding.setOnClickListener {
            val intent = Intent(this.context, SignInActivity::class.java)
            startActivity(intent)
        }
    }

}
