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
import com.example.internz.ui.profile.timeline.TimelineStoryHelper.timelineIdx
import com.example.internz.ui.story.StoryHelper
import kotlinx.android.synthetic.main.activity_timeline_storylist_intern.*


class TimelineStoryListInternActivity : AppCompatActivity() {


    private lateinit var rvTimelineStoryListIntern : RecyclerView
    private lateinit var timelineStoryListAdapterIntern: TimelineStoryListInternAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_storylist_intern)
        rvTimelineStoryListinit()
        backBtn()
        ingToast()

        txt_storylist_category.text = TimelineStoryHelper.category
        txt_timelineinnerlist_title.text = TimelineStoryHelper.title
        txt_timelineinnerlist_date.text = TimelineStoryHelper.period

    }

    /* 스토리 리스트뷰 세팅*/
    fun rvTimelineStoryListinit() {

        rvTimelineStoryListIntern = findViewById(R.id.rv_timeline_innerlist)
        timelineStoryListAdapterIntern = TimelineStoryListInternAdapter(this)
        rvTimelineStoryListIntern.adapter = timelineStoryListAdapterIntern
        rvTimelineStoryListIntern.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        /** 각 타임라인에 들어가면 보이는 '스토리 리스트' 서버 통신 요청 */
        Log.d("chohee", timelineIdx.toString())
        val timelineStoryListcall = ApiServiceImpl.service.requestStoryList(ApiServiceImpl.getToken(), timelineIdx.toString())
        timelineStoryListcall.enqueue(
            onSuccess = {
                if(it[it.size-1].isMe == 1) {
                    // 자신의 스토리 리스트가 아닐 경우 글쓰기 버튼 안보이게 하기
                    img_storylist_floatingBtn.visibility = View.INVISIBLE
                }else{
                    // 자신의 스토리 리스트일 경우 글쓰기 버튼 안보이게 하기
                    img_storylist_floatingBtn.visibility = View.VISIBLE
                }
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
    }

    /* 뒤로가기 화살표 클릭 리스너 */
    fun backBtn(){
        img_backBtn.setOnClickListener {
            finish()
        }
    }

    /* 준비중인 기능 토스트 */
    fun ingToast(){
        img_storylist_floatingBtn.setOnClickListener {
            Toast.makeText(this, "준비중인 기능입니다.", Toast.LENGTH_SHORT).show()
        }
    }


}



