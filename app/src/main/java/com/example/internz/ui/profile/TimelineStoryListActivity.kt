package com.example.internz.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.ProfileTimelineData
import com.example.internz.data.profile.TimelineStoryListData
import kotlinx.android.synthetic.main.fragment_profile.*

class TimelineStoryListActivity : AppCompatActivity() {


    private lateinit var rvTimelineStoryList : RecyclerView
    private lateinit var timelineStoryListadapter: TimelineStoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_storylist)

        rvTimelineStoryListinit()
    }

    fun rvTimelineStoryListinit() {

        rvTimelineStoryList = findViewById(R.id.rv_profile_timeline)
        timelineStoryListadapter = TimelineStoryListAdapter(this)
        rvTimelineStoryList.adapter = timelineStoryListadapter
        rvTimelineStoryList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        timelineStoryListadapter.data = listOf(
            TimelineStoryListData(
                desc = "영화 번역가는 AI 때문에 사라질 직업인가.",
                date = "12.23",
                img = R.drawable.home_recomm_story_img
            ),
            TimelineStoryListData(
                desc = "영화 번역가는 AI 때문에 사라질 직업인가.",
                date = "12.23",
                img = R.drawable.home_recomm_story_img
            ),
            TimelineStoryListData(
                desc = "영화 번역가는 AI 때문에 사라질 직업인가.",
                date = "12.23",
                img = R.drawable.home_recomm_story_img
            )
        )


    }
}