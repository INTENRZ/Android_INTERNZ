package com.example.internz.ui.profile.timeline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.TimelineStoryListInternData

class TimelineStoryListInternActivity : AppCompatActivity() {


    private lateinit var rvTimelineStoryListIntern : RecyclerView
    private lateinit var timelineStoryListAdapterIntern: TimelineStoryListInternAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_storylist_intern)

        rvTimelineStoryListinit()
    }

    fun rvTimelineStoryListinit() {

        rvTimelineStoryListIntern = findViewById(R.id.rv_timeline_innerlist)
        timelineStoryListAdapterIntern =
            TimelineStoryListInternAdapter(this)
        rvTimelineStoryListIntern.adapter = timelineStoryListAdapterIntern
        rvTimelineStoryListIntern.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        timelineStoryListAdapterIntern.data = listOf(
            TimelineStoryListInternData(
                desc = "영화 번역가는 AI 때문에 사라질 직업인가.",
                date = "12.23",
                img = R.drawable.home_recomm_story3_img
            ),
            TimelineStoryListInternData(
                desc = "영화 번역가는 AI 때문에 사라질 직업인가.",
                date = "12.23",
                img = R.drawable.home_recomm_story3_img
            ),
            TimelineStoryListInternData(
                desc = "영화 번역가는 AI 때문에 사라질 직업인가.",
                date = "12.23",
                img = R.drawable.home_recomm_story3_img
            )
        )


    }
}