package com.example.internz.feature.calendar

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.CalendarContract
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
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
import com.prolificinteractive.materialcalendarview.spans.DotSpan
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
    private var dayFormatter = SimpleDateFormat("MM-dd", Locale.KOREA)
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

        //YEAR-MONTH 통신 시작 //현재날짜로 캘린더 초기화
        formattedDate = monthFormatter.format(Date())
        Log.e("TAG", "YEAR-MONTH : ${formattedDate}")

        //공고 -> 캘린더 액티비티 이동시 띄울 전체 달력 데이터
        val call =
            ApiServiceImpl.service.requestCalenderMonth(
                formattedDate,
                ApiServiceImpl.getToken()
            )

        call.enqueue(
            onSuccess = {
                adapter.data = it
                adapter.notifyDataSetChanged()

                //텍스트 초기화
                txtCalendarEmpty.text = ""
            },
            onFail = { status, message ->
                run {
                    //Calendar에 공고 리스트가 존재하지 않는 경우 추가할 텍스트
                    txtCalendarEmpty.text = "공고를 추가해주세요."
                }
            }
        )


        //스크롤 이펙트 제거
        findViewById<RecyclerView>(R.id.rvCalendar).overScrollMode = View.OVER_SCROLL_NEVER

        //달력 띄우기
        calendarView = findViewById(R.id.Calendar)

        //달력에 공고 추가된 것에 따라 달력 view 다르게 그려주기
        calendarView.addDecorator(
            object : DayViewDecorator {
                //커스텀 여부에 따라 호출됨
                override fun decorate(view: DayViewFacade?) {
                    view?.addSpan(DotSpan(8f, Color.parseColor("#ffc200")))
                }

                //달력에 날짜 띄울때 decoration이 필요한지 판단
                override fun shouldDecorate(day: CalendarDay?): Boolean {
                    //TODO! 사용자가 공고 리스트에서 추가한 날짜와 일치하는 날만 decorator 추가
                    val formattedDate = dayFormatter.format(day?.date)
                    Log.e("TAG", "CalendarActivity : ${formattedDate}")

                    return CalendarHelper.monthDay.contains(formattedDate)
                }
            }
        )

        //TODAY 표시를 위한 데코레이터
        calendarView.addDecorator(
            object : DayViewDecorator {
                override fun decorate(view: DayViewFacade?) {
                    view?.addSpan(ForegroundColorSpan(Color.BLACK))
                    view?.addSpan(StyleSpan(Typeface.BOLD))
                    view?.addSpan(RelativeSizeSpan(1.2f))
                }

                //TODAY 표시
                override fun shouldDecorate(day: CalendarDay?): Boolean {
                    return CalendarDay.today() != null && day?.equals(CalendarDay.today())!!
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
                    //clearselection메서드 발견, 이걸로 전체 <-> 요일 변환 가능할 것 같음
                    //전체공고는 달력을 클릭했을때만 보여주기로 하고, 각 날짜를 클릭했을때의 공고를 보여주자
//                    formattedDate = dayFormatter.format(date.toString())
                    //선택한 날짜 YEAR-MONTH-DAY로 변환하기
                    var yearMonthDay = getDay(date.year, date.month, date.day)

                    //선택된 날짜에 대한 서버통신 필요
                    Log.e(
                        "TAG",
                        "CalendarActivity : YEAR-MONTH-DAY : ${yearMonthDay}일에 대한 서버 통신 필요"
                    )

                    //달력 클릭 날짜에 따른 서버 통신 구현
                    val call = ApiServiceImpl.service.requestCalenderDay(
                        yearMonthDay,
                        ApiServiceImpl.getToken()
                    )

                    call.enqueue(
                        onSuccess = {
                            adapter.data = it
                            adapter.notifyDataSetChanged()

                            //텍스트 초기화
                            txtCalendarEmpty.text = ""
                        },
                        onFail = { status, message ->
                            run {
                                adapter.data = emptyList()
                                adapter.notifyDataSetChanged()

                                //Calendar에 공고 리스트가 존재하지 않는 경우 추가할 텍스트
                                txtCalendarEmpty.text = "공고를 추가해주세요."
                            }
                        }
                    )
                }
            }
        )

        //달력의 상단바로 달(month)을 바꿀 때도 서버 통신으로 리스트 내역이 변경되도록 구현
        calendarView?.setOnMonthChangedListener(
            object : OnMonthChangedListener {
                override fun onMonthChanged(widget: MaterialCalendarView?, date: CalendarDay?) {
                    var yearMonth = getMonth(date?.year!!, date?.month!!)

                    Log.e("TAG", "CalendarActivity : YEAR-MONTH : ${yearMonth}")

                    val call = ApiServiceImpl.service.requestCalenderMonth(
                        ApiServiceImpl.getToken(),
                        yearMonth
                        )

                    call.enqueue(
                        onSuccess = {
                            adapter.data = it
                            adapter.notifyDataSetChanged()

                            //텍스트 초기화
                            txtCalendarEmpty.text = ""

                            Log.e("TAG", "CalendarActivity : onSuccess 메서드 실행됨")
                        },
                        onFail = { status, message ->
                            Log.e("TAG", "CalendarActivity : onFail 메서드 실행됨")

                            run {
                                //Calendar에 공고 리스트가 존재하지 않는 경우 추가할 텍스트
                                txtCalendarEmpty.text = "공고를 추가해주세요."
                            }
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

    private fun getMonth(year : Int, month : Int) : String {
        var m = month

        //month 변환
        when(m) {
            0 -> m = 1
            1 -> m = 2
            2 -> m = 3
            3 -> m = 4
            4 -> m = 5
            5 -> m = 6
            6 -> m = 7
            7 -> m = 8
            8 -> m = 9
            9 -> m = 10
            10 -> m = 11
            10 -> m = 12
        }

        return year.toString() + "-" + m.toString()
    }

    private fun getDay(year : Int, month : Int, day : Int) : String {
        var m = month
        var d = day.toString()

        //month 변환
        when(m) {
            0 -> m = 1
            1 -> m = 2
            2 -> m = 3
            3 -> m = 4
            4 -> m = 5
            5 -> m = 6
            6 -> m = 7
            7 -> m = 8
            8 -> m = 9
            9 -> m = 10
            10 -> m = 11
            10 -> m = 12
        }

        //day 변환
        if(day < 10) {
            d = "0".plus(d)
        }

        return year.toString() + "-" + m + "-" + d
    }
}