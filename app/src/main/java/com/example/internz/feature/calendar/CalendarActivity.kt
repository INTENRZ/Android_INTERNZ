package com.example.internz.feature.calendar

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.CalendarContract
import android.util.EventLog
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.prolificinteractive.materialcalendarview.*
import kotlinx.android.synthetic.main.activity_calendar.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


class CalendarActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CalendarAdapter

    //달력
    private lateinit var calendarView: com.prolificinteractive.materialcalendarview.MaterialCalendarView

    //날짜 형식 지정
    private var dayFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    private var monthFormatter = SimpleDateFormat("yyyy-MM", Locale.KOREA)
    private var formattedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarFunction()
    }

    private fun calendarFunction() {
        //하단 recycler view 목록 지정
        recyclerView = findViewById(R.id.rvCalendar)
        adapter = CalendarAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //YEAR-MONTH 통신 시작
//        val localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
//        val date = localDateTime.toString().substring(0, 7)
        formattedDate = monthFormatter.format(Date())
        Log.e("TAG", "YEAR-MONTH : ${formattedDate}")

        //공고 -> 캘린더 액티비티 이동시 띄울 전체 달력 데이터
        val call =
            ApiServiceImpl.service.requestCalenderMonth(ApiServiceImpl.getToken(), formattedDate)

        call.enqueue(
            onSuccess = {
                adapter.data = it
                adapter.notifyDataSetChanged()

                Log.e("TAG", "CalendarActivity : 하단 공고 띄우기 onSuccess 메서드 실행됨")
            },
            onFail = { status, message ->
                Log.e("TAG", "CalendarActivity : onFail 메서드 실행됨")
            }
        )

        //Calendar에 공고 리스트가 존재하지 않는 경우 추가할 텍스트
        if (adapter.itemCount == 0) {
            txtCalendarEmpty.text = "공고를 추가해주세요."
        } else {
            txtCalendarEmpty.text = ""
        }

        //스크롤 이펙트 제거
        findViewById<RecyclerView>(R.id.rvCalendar).overScrollMode = View.OVER_SCROLL_NEVER

        //달력 띄우기
        calendarView = findViewById(R.id.Calendar)

        //달력에 공고 추가된 것에 따라 달력 view 다르게 그려주기
        calendarView.addDecorator(
            object : DayViewDecorator {
                //커스텀 여부에 따라 호출됨
                override fun decorate(view: DayViewFacade?) {
                    view?.setBackgroundDrawable(getDrawable(R.drawable.profile_img)!!)
                    //TODO! 달력에 추가하는 이미지 수정 필요
                }

                //달력에 날짜 띄울때 decoration이 필요한지 판단
                override fun shouldDecorate(day: CalendarDay?): Boolean {
                    //TODO! 사용자가 공고 리스트에서 추가한 날짜와 일치하는 날만 decorator 추가
                    if (day?.day == 5)
                        return true
                    else
                        return false
                }
            }
        )

        //달력의 click event(YEAR-MONTH-DAY)
        calendarView?.setOnDateChangedListener(
            object : OnDateSelectedListener {
                override fun onDateSelected(
                    widget: MaterialCalendarView,
                    date: CalendarDay,
                    selected: Boolean
                ) {
                    //전체공고는 달력을 클릭했을때만 보여주기로 하고, 각 날짜를 클릭했을때의 공고를 보여주자
                    formattedDate = dayFormatter.format(date.toString())

                    val date = date.day //선택된 날짜
                    //선택된 날짜에 대한 서버통신 필요
                    Log.e(
                        "TAG",
                        "CalendarActivity : YEAR-MONTH-DAY : ${formattedDate}일에 대한 서버 통신 필요"
                    )

                    val call = ApiServiceImpl.service.requestCalenderDay(
                        formattedDate,
                        ApiServiceImpl.getToken()
                    )

                    call.enqueue(
                        onSuccess = {
                            adapter.data = it
                            adapter.notifyDataSetChanged()

                            Log.e("TAG", "CalendarActivity : onSuccess 메서드 실행됨")
                        },
                        onFail = { status, message ->
                            Log.e("TAG", "CalendarActivity : onFail 메서드 실행됨")
                        }
                    )
                }
            }
        )

        //달력의 상단바로 달(month)을 바꿀 때도 서버 통신으로 리스트 내역이 변경되도록 구현
        calendarView?.setOnMonthChangedListener(
            object : OnMonthChangedListener {
                override fun onMonthChanged(widget: MaterialCalendarView?, date: CalendarDay?) {
                    formattedDate = monthFormatter.format(date.toString())
                    Log.e("TAG", "CalendarActivity : YEAR-MONTH : ${formattedDate}")

                    val call = ApiServiceImpl.service.requestCalenderMonth(
                        ApiServiceImpl.getToken(),
                        formattedDate
                        )

                    call.enqueue(
                        onSuccess = {
                            adapter.data = it
                            adapter.notifyDataSetChanged()

                            Log.e("TAG", "CalendarActivity : onSuccess 메서드 실행됨")
                        },
                        onFail = { status, message ->
                            Log.e("TAG", "CalendarActivity : onFail 메서드 실행됨")
                        }
                    )
                }
            }
        )

        //달력에 공고 추가

        //calendar -> 공고 이동 imageview click listener
        imgCalendarToNoti.setOnClickListener {
            this.finish()
        }
    }
}

//    //YEAR-MONTH 형태로 반환 (달(month)별 캘린더 조회에 필요)
//    private fun getDate(year : Int, month : Int) : String {
//        var m : String? = null
//        when(month) {
//            0 -> m = "01"
//            1 -> m = "02"
//            2 -> m = "03"
//            3 -> m = "04"
//            4 -> m = "05"
//            5 -> m = "06"
//            6 -> m = "07"
//            7 -> m = "08"
//            8 -> m = "09"
//            9 -> m = "10"
//            10 -> m = "11"
//            11 -> m = "12"
//        }
//
//        return year.toString() + "-" + m
//    }
//}