package com.example.internz.ui.profile.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.ProfileTimelineData

class OtherProfileActivity : AppCompatActivity() {

    private lateinit var rv_otherTimeline: RecyclerView
    private lateinit var adapter_otherTimeline: MainProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_profile)

        rvSetting()
    }

    /* 타임라인 리사이클러뷰 세팅 */
    fun rvSetting(){
        rv_otherTimeline = findViewById(R.id.rv_otherProfile_timeline)
        adapter_otherTimeline = MainProfileAdapter(this)
        rv_otherTimeline.adapter = adapter_otherTimeline
        rv_otherTimeline.layoutManager = LinearLayoutManager(this)
        adapter_otherTimeline.data = listOf(
            ProfileTimelineData(
                timelineCategory = "인턴",
                timelineTitle = "NAVER SNOW Jam Studio 기획/운영팀",
                timelinePeriodStart = "19.01.01",
                timelinePeriodEnd = "19.07.01"
            )
        )
    }
}
