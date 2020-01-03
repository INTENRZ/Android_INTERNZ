package com.example.internz.feature.homecustomnotification



import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.internz.R
import com.example.internz.feature.web.WebViewActivity
import kotlinx.android.synthetic.main.fragment_home_custom_first.*


class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_custom_first, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        contentFirst.setOnClickListener {
            val intent = Intent(this@FirstFragment.context, WebViewActivity::class.java)
                .putExtra(
                    "url",
                    "https://recruit.navercorp.com/naver/job/detail/developer?annoId=20003496&classId=&jobId=&entTypeCd=004&searchTxt=&searchSysComCd="
                )

            startActivity(intent)
        }
    }
}


