package com.example.internz.ui.profile.timeline

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.StoryAddActivity
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.ui.profile.timeline.TimelineStoryHelper.timelineIdx
import kotlinx.android.synthetic.main.activity_timeline_storylist_intern.*


class TimelineStoryListInternActivity : AppCompatActivity() {


    private lateinit var rvTimelineStoryListIntern : RecyclerView
    private lateinit var timelineStoryListAdapterIntern: TimelineStoryListInternAdapter

    companion object{
        const val STORY_ADD_REQUEST = 1000
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_storylist_intern)
        rvTimelineStoryListinit()
        backBtn()
        laterDevelop()
        addStoryBtn()

        txt_storylist_category.text = TimelineStoryHelper.category
        txt_timelineinnerlist_title.text = TimelineStoryHelper.title
        txt_timelineinnerlist_date.text = TimelineStoryHelper.period

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == STORY_ADD_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                requestToServer()
            }
        }
    }

    /* 스토리 리스트뷰 세팅*/
    fun rvTimelineStoryListinit() {

        rvTimelineStoryListIntern = findViewById(R.id.rv_timeline_innerlist)
        timelineStoryListAdapterIntern = TimelineStoryListInternAdapter(this)
        rvTimelineStoryListIntern.adapter = timelineStoryListAdapterIntern
        rvTimelineStoryListIntern.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        /** 각 타임라인에 들어가면 보이는 '스토리 리스트' 서버 통신 요청 */
//        val timelineStoryListcall = ApiServiceImpl.service.requestStoryList(ApiServiceImpl.getToken(), timelineIdx.toString())
//        timelineStoryListcall.enqueue(
//            onSuccess = {
//                if(it[it.size-1].isme == 0) {
//                    // 자신의 스토리 리스트가 아닐 경우 글쓰기 버튼 안보이게 하기
//                    img_storylist_floatingBtn.visibility = View.INVISIBLE
//                }else{
//                    // 자신의 스토리 리스트일 경우 글쓰기 버튼 안보이게 하기
//                    img_storylist_floatingBtn.visibility = View.VISIBLE
//                }
//                timelineStoryListAdapterIntern.data = it
//                timelineStoryListAdapterIntern.notifyDataSetChanged()
//            },
//            onFail = {status, message ->
//                // 자신의 타임라인이 하나도 존재하지 않을 때 "존재하지 않는 스토리 입니다." 텍스트 띄우기
//                if(message == "존재하지 않는 스토리 입니다."){
//                    txt_none.visibility = View.VISIBLE
//                }
//            }
//        )
        requestToServer()
    }

    /** 각 타임라인에 들어가면 보이는 '스토리 리스트' 서버 통신 요청 */
    fun requestToServer(){
        val timelineStoryListcall = ApiServiceImpl.service.requestStoryList(ApiServiceImpl.getToken(), timelineIdx.toString())
        timelineStoryListcall.enqueue(
            onSuccess = {
                if(it[it.size-1].isme == 0) {
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
    fun laterDevelop(){
        img_storylist_floatingBtn.setOnClickListener {
            Toast.makeText(this, "스토리 작성 기능을 준비중입니다. 조금만 기다려주세요.", Toast.LENGTH_SHORT).show()
        }

        img_menu.setOnClickListener {
            Toast.makeText(this, "설정 기능을 준비중입니다. 조금만 기다려주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    /* 스토리 추가 */
    fun addStoryBtn(){
        img_storylist_floatingBtn.setOnClickListener {
            var intent = Intent(this, StoryAddActivity::class.java)
            startActivityForResult(intent, STORY_ADD_REQUEST)
        }
    }
}



