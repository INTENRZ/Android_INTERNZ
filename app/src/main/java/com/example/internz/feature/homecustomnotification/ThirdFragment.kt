package com.example.internz.feature.homecustomnotification


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.internz.R
import com.example.internz.feature.web.WebViewActivity
import kotlinx.android.synthetic.main.fragment_home_custom_third.*


class ThirdFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_custom_third, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        contentThird.setOnClickListener {
            val intent = Intent(this@ThirdFragment.context, WebViewActivity::class.java)
                .putExtra("url", "http://www.kpf.or.kr/site/kpf/ex/board/View.do?cbIdx=268&bcIdx=22687" )

            startActivity(intent)
        }
    }


}
