package com.example.internz.ui.profile.timeline

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.data.profile.TimelineStoryListInternData
import com.example.internz.ui.profile.timeline.TimelineStoryHelper.timelineIdx
import kotlinx.android.synthetic.main.activity_timeline_storylist_intern.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call

class TimelineStoryListInternActivity : AppCompatActivity() {


    private lateinit var rvTimelineStoryListIntern : RecyclerView
    private lateinit var timelineStoryListAdapterIntern: TimelineStoryListInternAdapter

    // 이거 null 임
//    val extras = intent.extras
//    val timelineIdx : String = extras?.getString("timelineidx").toString()

//    Bundle extras = getIntent().getExtras();
//String foo = extras.getString("FOO");





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_storylist_intern)
        rvTimelineStoryListinit()

    }

    /* 스토리 리스트뷰 세팅*/
    fun rvTimelineStoryListinit() {

        rvTimelineStoryListIntern = findViewById(R.id.rv_timeline_innerlist)
        timelineStoryListAdapterIntern = TimelineStoryListInternAdapter(this)
        rvTimelineStoryListIntern.adapter = timelineStoryListAdapterIntern
        rvTimelineStoryListIntern.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        /** 각 타임라인에 들어가면 보이는 '스토리 리스트' 서버 통신 요청 */
        val timelineStoryListcall = ApiServiceImpl.service.requestStoryList(ApiServiceImpl.getToken(), timelineIdx.toString())
        timelineStoryListcall.enqueue(
            onSuccess = {
                timelineStoryListAdapterIntern.data = it
                timelineStoryListAdapterIntern.notifyDataSetChanged()
            },
            onFail = {status, message ->
                // 자신의 타임라인이 하나도 존재하지 않을 때 "존재하지 않는 스토리 입니다." 텍스트 띄우기
                if(message == "존재하지 않는 스토리 입니다."){
                    txt_none.visibility = View.VISIBLE
                }
            }
        )


//        timelineStoryListAdapterIntern.data = listOf(
//            TimelineStoryListInternData(
//                desc = "영화 번역가는 AI 때문에 사라질 직업인가.",
//                date = "12.23",
//                img = R.drawable.home_recomm_story3_img
//            ),
//            TimelineStoryListInternData(
//                desc = "영화 번역가는 AI 때문에 사라질 직업인가.",
//                date = "12.23",
//                img = R.drawable.home_recomm_story3_img
//            ),
//            TimelineStoryListInternData(
//                desc = "영화 번역가는 AI 때문에 사라질 직업인가.",
//                date = "12.23",
//                img = R.drawable.home_recomm_story3_img
//            )
//        )


    }
}



