package com.example.internz.ui.profile.timeline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.TimelineStoryListExternalData


class TimelineStoryListExternalActivity : AppCompatActivity() {


    private lateinit var rvTimelineStoryListExternal : RecyclerView
    private lateinit var timelineStoryListAdapterExternal: TimelineStoryListExternalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_storylist_external)

        rvTimelineStoryListinit()
    }

    fun rvTimelineStoryListinit() {

        rvTimelineStoryListExternal = findViewById(R.id.rv_profile_timeline)
        timelineStoryListAdapterExternal =
            TimelineStoryListExternalAdapter(this)
        rvTimelineStoryListExternal.adapter = timelineStoryListAdapterExternal
        rvTimelineStoryListExternal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        timelineStoryListAdapterExternal.data = listOf(
            TimelineStoryListExternalData(
                desc = "영화 번역가는 AI 때문에 사라질 직업인가.",
                date = "12.23",
                img = R.drawable.home_recomm_story3_img
            ),
            TimelineStoryListExternalData(
                desc = "영화 번역가는 AI 때문에 사라질 직업인가.",
                date = "12.23",
                img = R.drawable.home_recomm_story3_img
            ),
            TimelineStoryListExternalData(
                desc = "영화 번역가는 AI 때문에 사라질 직업인가.",
                date = "12.23",
                img = R.drawable.home_recomm_story3_img
            )
        )


    }
}