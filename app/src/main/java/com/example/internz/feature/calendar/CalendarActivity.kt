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
import kotlinx.android.synthetic.main.activity_sign_up2.view.*
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
    private var allFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    private var formattedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarFunction()
    }

    private fun calendarFunction() {
        initialVariable()
        makeRecyclerView()
        displayCalendar()
        getDayData()

        //calendar -> 공고 이동 imageview click listener
        imgCalendarToNoti.setOnClickListener {
            this.finish()
        }
    }

    //변수 초기화
    private fun initialVariable() {
        //RecyclerView
        recyclerView = findViewById(R.id.rvCalendar)
        adapter = CalendarAdapter(this)

        //스크롤 이펙트 제거
        findViewById<RecyclerView>(R.id.rvCalendar).overScrollMode = View.OVER_SCROLL_NEVER

        //달력
        calendarView = findViewById(R.id.Calendar)
    }

    //하단 Recycler View 띄우기
    private fun makeRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //YEAR-MONTH
        val yearMonth = monthFormatter.format(Date())

        val call = ApiServiceImpl.service.requestCalenderMonth(
            yearMonth,
            ApiServiceImpl.getToken()
        )

        call.enqueue(
            onSuccess = {
                adapter.data = it
                adapter.notifyDataSetChanged()

                //전체 공고 데이터 Helper에 저장
                for (i in it) {
                    CalendarHelper.setMonthDay(i.end_date)
                }
                Log.e("TAG", "이번달 전체 공고 : ${CalendarHelper.monthDay}")

                //데코레이션 추가
                decorationSetting()
            },
            onFail = {
                status, message -> Log.e("TAG", "YEAR_MONTH에 해당하는 공고가 없습니다.")

                run {
                    if(status == 381) { //MONTH에 해당하는 공고가 없는 경우
                        txtCalendarEmpty.text = "공고를 추가해주세요."

                        //recyclerview 초기화
                        adapter.data = emptyList()
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }

    //decoration setting
    private fun decorationSetting() {
        calendarView.addDecorators( //상단 calendar의 공고 decoration
            object : DayViewDecorator {
                override fun decorate(view: DayViewFacade?) {
                    //텍스트 밑에 점 찍기
                    view?.addSpan(DotSpan(8f, Color.parseColor("#ffc200")))
                }

                override fun shouldDecorate(day: CalendarDay?): Boolean {
                    formattedDate = dayFormatter.format(day?.date)

                    return CalendarHelper.monthDay.contains(formattedDate)
                }
            },
            object : DayViewDecorator { //상단 calendar의 today decoration
                override fun decorate(view: DayViewFacade?) {
                    //텍스트 형식 지정
                    view?.addSpan(StyleSpan(Typeface.BOLD))
                    view?.addSpan(ForegroundColorSpan(Color.BLACK))
//                    view?.addSpan(RelativeSizeSpan(1.4f))
                }

                override fun shouldDecorate(day: CalendarDay?): Boolean {
                    return CalendarDay.today() != null && day?.equals(CalendarDay.today())!!
                }
            }
        )
    }

    //상단 calendar의 공고 decoration setting
    private fun markNotification() {
        object : DayViewDecorator {
            override fun decorate(view: DayViewFacade?) {
                //텍스트 밑에 점 찍기
                view?.addSpan(DotSpan(8f, Color.parseColor("ffc200")))
            }

            override fun shouldDecorate(day: CalendarDay?): Boolean {
                formattedDate = dayFormatter.format(day?.date)
                return CalendarHelper.monthDay.contains(formattedDate)
            }
        }
    }

    //상단 calendar의 월별 캘린더 띄우기
    private fun displayCalendar() {
        calendarView?.setOnMonthChangedListener(
            object : OnMonthChangedListener {
                override fun onMonthChanged(widget: MaterialCalendarView?, date: CalendarDay?) {
                    CalendarHelper.monthDay = emptySet() //저장된 일자 초기화
                    formattedDate = monthFormatter.format(date?.date)

                    val call = ApiServiceImpl.service.requestCalenderMonth(
                        formattedDate,
                        ApiServiceImpl.getToken()
                    )

                    call.enqueue(
                        onSuccess = {
                            //텍스트 초기화
                            txtCalendarEmpty.text = ""

                            adapter.data = it
                            adapter.notifyDataSetChanged()

                            //전체 공고 데이터 Helper에 저장
                            for (i in it) {
                                CalendarHelper.setMonthDay(i.end_date)
                            }

                            //decoration
                            decorationSetting()
                        },
                        onFail = {
                            status, message -> Log.e("TAG", "달을 바꿨을때의 통신 onFail")
                            run {
                                txtCalendarEmpty.text = "공고를 추가해주세요."

                                adapter.data = emptyList()
                                adapter.notifyDataSetChanged()
                            }
                        }
                    )
                }
            }
        )
    }

    //달력 날짜에 따른 서버 통신 구현
    private fun getDayData() {
        calendarView?.setOnDateChangedListener(
            object : OnDateSelectedListener {
                override fun onDateSelected(
                    widget: MaterialCalendarView,
                    date: CalendarDay,
                    selected: Boolean
                ) {
                    //선택한 날짜 YEAR_MONTh_DAY로 변환
                    formattedDate = allFormatter.format(date?.date)

                    //달력 클릭 날짜에 따른 서버 통신 구현
                    val call = ApiServiceImpl.service.requestCalenderDay(
                        formattedDate,
                        ApiServiceImpl.getToken()
                    )

                    call.enqueue(
                        onSuccess = {
                            //배경 텍스트 초기화
                            txtCalendarEmpty.text = ""

                            if (selected) {
                                widget.clearFocus()
                            }

                            adapter.data = it
                            adapter.notifyDataSetChanged()
                        },
                        onFail = {
                            status, message -> Log.e("TAG", "YEAR_MONTH_DAY onfail")
                            run {
                                if(status == 380) {
                                    //배경 텍스트 초기화
                                    txtCalendarEmpty.text = "공고를 추가해주세요."

                                    adapter.data = emptyList()
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    )
                }
            }
        )
    }
}