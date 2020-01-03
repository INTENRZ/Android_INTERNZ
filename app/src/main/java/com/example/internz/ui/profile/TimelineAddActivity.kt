package com.example.internz.ui.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.BaseResponse
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.profile.TimelineAddRequestData
import com.example.internz.data.timeline.TimelineCategoryData
import com.example.internz.feature.SelectHelper
import kotlinx.android.synthetic.main.activity_timeline_add.*
import retrofit2.Call

class TimelineAddActivity : AppCompatActivity() {

    private lateinit var rv_timeline_category: RecyclerView
    private lateinit var adapter_timeline_category : TimelineCategoryAdapter

    // 서버에 넘겨줄 데이터들 선언
    private lateinit var category : String
    private lateinit var title : String
    private lateinit var startDate : String
    private lateinit var endDate : String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_add)
        val et_title = findViewById<EditText>(R.id.edt_timelineadd_title)
        val et_startYear = findViewById<EditText>(R.id.edt_timelineadd_period_year)
        val et_startMonth = findViewById<EditText>(R.id.edt_timelineadd_period_month)
        val et_startDay = findViewById<EditText>(R.id.edt_timelineadd_period_day)
        val et_endYear = findViewById<EditText>(R.id.edt_timelineadd_period_year_end)
        val et_endMonth = findViewById<EditText>(R.id.edt_timelineadd_period_month_end)
        val et_endDay = findViewById<EditText>(R.id.edt_timelineadd_period_day_end)

        //timelineCategoryRv()

        /* 등록버튼 클릭시 edittext에 작성한 텍스트 받기 + 서버 통신 */
        txt_timelineadd_add.setOnClickListener {
            val startYear = et_startYear.text.toString()
            val startMonth = et_startMonth.text.toString()
            val startDay = et_startDay.text.toString()
            val endYear = et_endYear.text.toString()
            val endMonth = et_endMonth.text.toString()
            val endDay = et_endDay.text.toString()

            // 타임라인 추가하기 위해 서버에 넘겨줄 데이터들
            title = et_title.text.toString()
            startDate = startYear + "-"  + startMonth + "-" + startDay
            endDate = endYear + "-"  + endMonth + "-" + endDay
            when(SelectHelper.categoryWhat){
                0 -> category = "인턴"
                1 -> category = "대외활동"
                2 -> category = "공모전"
                3 -> category = "동아리"
                4 -> category = "자격증"
                5 -> category = "기타"
            }

            /* 타임라인 추가 서버 요청 */
            val call: Call<BaseResponse<TimelineAddRequestData>> = ApiServiceImpl.service.requestTimelineAdd(ApiServiceImpl.getToken(), TimelineAddRequestData(
                title,
                startDate,
                endDate,
                category)
            )

            call.enqueue(
                onSuccess = {
                    Toast.makeText(this, "타임라인이 추가되었습니다.", Toast.LENGTH_SHORT).show()
                    var intent = Intent()
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                },
                onFail = {status, message ->  toast(message)
                }
            )

        }

        deleteBtn()


    }

    /* 타임라인 추가할 때 선택해야 하는 카테고리 그리디 리사이클러뷰 세팅 */
//    fun timelineCategoryRv(){
//        rv_timeline_category = findViewById(R.id.rv_timelineadd)
//        adapter_timeline_category = TimelineCategoryAdapter(this)
//        rv_timeline_category.adapter = adapter_timeline_category
//        rv_timeline_category.layoutManager = GridLayoutManager(this, 4)
//
//        /* 카테고리 6개 배치 그리드 리사이클러뷰*/
//        adapter_timeline_category.data = listOf(
//            TimelineCategoryData(
//                category = "인턴"
//            ),
//            TimelineCategoryData(
//                category = "대외활동"
//            ),
//            TimelineCategoryData(
//                category = "공모전"
//            ),
//            TimelineCategoryData(
//                category = "동아리"
//            ),
//            TimelineCategoryData(
//                category = "자격증"
//            ),
//            TimelineCategoryData(
//                category = "기타"
//            )
//        )
//        adapter_timeline_category.notifyDataSetChanged()
//    }



    /* 엑스 버튼 세팅 */
    fun deleteBtn(){
        img_timelineadd_delete.setOnClickListener {
            finish()
        }
    }



}
