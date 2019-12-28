package com.example.internz.ui.profile


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.TimelineAddActivity
import com.example.internz.data.profile.ProfileTimelineData
import kotlinx.android.synthetic.main.fragment_profile.*

class TimelineFragment : Fragment() {

    private lateinit var rv_profile_timeline: RecyclerView
    private lateinit var adapter_profile_timeline: TimelineAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvInit()
        floatingClick()
    }

    fun rvInit(){
        rv_profile_timeline = activity!!.findViewById(R.id.rv_profile_timeline)
        adapter_profile_timeline = TimelineAdapter(context!!)
        rv_profile_timeline.adapter = adapter_profile_timeline
        rv_profile_timeline.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter_profile_timeline.data = listOf(
            ProfileTimelineData(
                timelineCategory = "인턴",
                timelineTitle = "NAVER SNOW Jam Studio 기획/운영팀",
                timelinePeriodStart = "19.01.01",
                timelinePeriodEnd = "19.07.01"
            ),
            ProfileTimelineData(
                timelineCategory = "인턴",
                timelineTitle = "SK Telecom 서포터즈 T프렌즈 2기",
                timelinePeriodStart = "19.01.01",
                timelinePeriodEnd = "19.07.01"
            ),
            ProfileTimelineData(
                timelineCategory = "인턴",
                timelineTitle = "SOPT 25기 기획팀 ",
                timelinePeriodStart = "19.01.01",
                timelinePeriodEnd = "19.07.01"
            ),
            ProfileTimelineData(
                timelineCategory = "인턴",
                timelineTitle = "SK Telecom 서포터즈 T프렌즈 2기",
                timelinePeriodStart = "19.01.01",
                timelinePeriodEnd = "19.07.01"
            ),
            ProfileTimelineData(
                timelineCategory = "인턴",
                timelineTitle = "인턴즈짱",
                timelinePeriodStart = "19.01.01",
                timelinePeriodEnd = "19.07.01"
            ),
            ProfileTimelineData(
                timelineCategory = "인턴",
                timelineTitle = "리사이클러뷰 임",
                timelinePeriodStart = "19.01.01",
                timelinePeriodEnd = "19.07.01"
            ),
            ProfileTimelineData(
                timelineCategory = "인턴",
                timelineTitle = "타임라인임~",
                timelinePeriodStart = "19.01.01",
                timelinePeriodEnd = "19.07.01"
            )
        )
        adapter_profile_timeline.notifyDataSetChanged()
    }

    fun floatingClick(){
        img_profile_floating.setOnClickListener{
            val intent = Intent(context, TimelineAddActivity::class.java)
            startActivity(intent)
        }
    }
}