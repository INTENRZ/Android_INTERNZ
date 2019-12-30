package com.example.internz.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.timeline.TimelineCategoryData
import kotlinx.android.synthetic.main.activity_timeline_add.*

class TimelineAddActivity : AppCompatActivity() {

    private lateinit var rv_timeline_category: RecyclerView
    private lateinit var adapter_timeline_category : TimelineCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_add)

        timelineCategoryRv()
        deleteBtn()
        addbtn()

    }

    /* 타임라인 추가할 때 선택해야 하는 카테고리 그리디 리사이클러뷰 세팅 */
    fun timelineCategoryRv(){
        rv_timeline_category = findViewById(R.id.rv_timelineadd)
        adapter_timeline_category = TimelineCategoryAdapter(this)
        rv_timeline_category.adapter = adapter_timeline_category
        rv_timeline_category.layoutManager = GridLayoutManager(this, 4)


//        /* 프로필 타임라인 조회 서버 통신 */
//        val timelineCall: Call<BaseResponse<ProfileTimelineData>> = ApiServiceImpl.service.responseProfileTimelineList(ApiServiceImpl.getToken())
//
//        timelineCall.enqueue(
//
//            onSuccess = {
//                val data: ProfileTimelineData = it
//                Log.d("chohee", data.toString())
//            },
//            onFail = {status, message ->  toast(message)
//            }
//        )

        adapter_timeline_category.data = listOf(
            TimelineCategoryData(
                category = "인턴"
            ),
            TimelineCategoryData(
                category = "대외활동"
            ),
            TimelineCategoryData(
                category = "공모전"
            ),
            TimelineCategoryData(
                category = "동아리"
            ),
            TimelineCategoryData(
                category = "자격증"
            ),
            TimelineCategoryData(
                category = "기타"
            )
        )
        adapter_timeline_category.notifyDataSetChanged()
    }

    /* 엑스 버튼 세팅 */
    fun deleteBtn(){
        img_timelineadd_delete.setOnClickListener {
            finish()
        }
    }

    /* 등록 버튼 세팅 */
    fun addbtn(){
        txt_timelineadd_add.setOnClickListener {

        }
    }

}
