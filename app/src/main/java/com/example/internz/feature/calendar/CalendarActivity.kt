package com.example.internz.feature.calendar

import android.graphics.Insets.add
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.applikeysolutions.cosmocalendar.selection.SingleSelectionManager
import com.applikeysolutions.cosmocalendar.utils.SelectionType
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import kotlinx.android.synthetic.main.activity_calendar.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import kotlin.collections.HashSet


class CalendarActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CalendarAdapter

    //달력
//    private lateinit var calendar: java.util.Calendar
    private lateinit var calendarView: com.applikeysolutions.cosmocalendar.view.CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarFunction()
    }

    private fun calendarFunction() {
        //변수 초기화
       // calendarView = findViewById(R.id.Calendar)

        //달력의 방향 지정
        calendarView.calendarOrientation = 0

        //달력 타입 지정
        calendarView.selectionType = SelectionType.SINGLE

        //달력 click event
        if (calendarView.selectionManager is SingleSelectionManager) {
            val singleSelectionManager = calendarView.selectionManager


            Log.e("TAG", singleSelectionManager.toString())
        }

        //하단 recycler view 목록 지정
        recyclerView = findViewById(R.id.rvCalendar)
        adapter = CalendarAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
        val date = localDateTime.toString().substring(0, 7)
        Log.e("TAG", "${date}")

        //공고 -> 캘린더 액티비티 이동시 띄울 전체 달력 데이터
        val call = ApiServiceImpl.service.requestCalenderMonth(ApiServiceImpl.getToken(), date)

        call.enqueue(
            onSuccess = {
                adapter.data = it
                adapter.notifyDataSetChanged()

                Log.e("TAG", "CalendarActivity : onSuccess 메서드 실행됨")
            },
            onFail = {
                    status, message -> Log.e("TAG", "CalendarActivity : onFail 메서드 실행됨")
            }
        )

        if (adapter.itemCount == 0) {
            txtCalendarEmpty.text = "공고를 추가해주세요."
        } else {
            txtCalendarEmpty.text = ""
        }

        //스크롤 이펙트 제거
        findViewById<RecyclerView>(R.id.rvCalendar).overScrollMode = View.OVER_SCROLL_NEVER
    }
}

//    private fun calendarFunction() {
//        //하단 recycler view 목록 지정
//        recyclerView = findViewById(R.id.rvCalendar)
//        adapter = CalendarAdapter(this)
//
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        val localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
//        val date = localDateTime.toString().substring(0, 7)
//        Log.e("TAG", "${date}")
//
//        //공고 -> 캘린더 액티비티 이동시 띄울 전체 달력 데이터
//        val call = ApiServiceImpl.service.requestCalenderMonth(ApiServiceImpl.getToken(), date)
//
//        call.enqueue(
//            onSuccess = {
//                adapter.data = it
//                adapter.notifyDataSetChanged()
//
//                Log.e("TAG", "CalendarActivity : onSuccess 메서드 실행됨")
//            },
//            onFail = {
//                status, message -> Log.e("TAG", "CalendarActivity : onFail 메서드 실행됨")
//            }
//        )
//
//        if (adapter.itemCount == 0) {
//            txtCalendarEmpty.text = "공고를 추가해주세요."
//        } else {
//            txtCalendarEmpty.text = ""
//        }
//
//        //스크롤 이펙트 제거
//        findViewById<RecyclerView>(R.id.rvCalendar).overScrollMode = View.OVER_SCROLL_NEVER
//
//        //Calendar Setting
//        calendarView = findViewById(R.id.Calendar)
//        var events = listOf<EventDay>()
//
//        //image setting
//        calendarView.setForwardButtonImage(resources.getDrawable(R.drawable.notice_calendar_next_ic))
//        calendarView.setPreviousButtonImage(resources.getDrawable(R.drawable.notice_calendar_before_ic))
//
//        //add one events
//        calendar = java.util.Calendar.getInstance()
//        calendar.add(java.util.Calendar.DAY_OF_MONTH, 5) //3일에 이벤트 추가
//
//        //TODO ERROR! 무슨짓을 하던 다음달로 설정됨
//        events += events.plus(EventDay(calendar, R.drawable.basicprofile_img))
//
//        var calendar2: java.util.Calendar = java.util.Calendar.getInstance()
//        calendar2.add(java.util.Calendar.DAY_OF_MONTH, 7) //5일에 이벤트 추가
//        events += events.plus(EventDay(calendar2, R.drawable.profile_img))
//
//        //event setting
//        calendarView.setEvents(events)
//
//        //set click listener for calendar
//        calendarView.setOnDayClickListener { eventDay ->
//            run {
//                //TODO! 서버 통신 후 데이터 가져오기
//                Log.e("TAG", eventDay.calendar.time.date.toString())
//                Log.e("TAG", "한번 더")
//            }
//        }
//
//        //calendar -> 공고 이동 imageview click listener
//        imgCalendarToNoti.setOnClickListener {
//            this.finish()
//        }
//    }
//
