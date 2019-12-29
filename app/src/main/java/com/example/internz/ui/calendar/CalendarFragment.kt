package com.example.internz.ui.calendar


import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.example.internz.R
import com.example.internz.common.toast
import com.example.internz.data.calendar.CalendarDataTemporal
import kotlinx.android.synthetic.main.fragment_calendar.*


/**
 * A simple [Fragment] subclass.
 */
class CalendarFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : CalendarAdapter

    //달력
    private lateinit var calendar : java.util.Calendar
    private lateinit var calendarView: CalendarView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        calendarFunction(view)
        return view
    }

    private fun calendarFunction(view : View) {

        //RecyclerView setting
        recyclerView = view.findViewById(R.id.rvCalendar)
        adapter = CalendarAdapter(view.context)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        adapter.data = CalendarDataTemporal().getCalendarData()
        adapter.notifyDataSetChanged()

        //스크롤 이펙트 제거
        rvCalendar.overScrollMode = View.OVER_SCROLL_NEVER

        //Calendar Setting
        calendarView = view.findViewById(R.id.Calendar)
        var events = listOf<EventDay>()

        //add one events
        calendar = java.util.Calendar.getInstance()
        calendar.add(java.util.Calendar.DAY_OF_MONTH, 5) //3일에 이벤트 추가

        //TODO ERROR! 무슨짓을 하던 다음달로 설정됨
        events += events.plus(EventDay(calendar, R.drawable.basicprofile_img))

        var calendar2 : java.util.Calendar = java.util.Calendar.getInstance()
        calendar2.add(java.util.Calendar.DAY_OF_MONTH, 7) //5일에 이벤트 추가
        events += events.plus(EventDay(calendar2, R.drawable.profile_img))

        //event setting
        calendarView.setEvents(events)

        //set click listener for calendar
        calendarView.setOnDayClickListener { eventDay ->
            run {
                //TODO! 서버 통신 후 데이터 가져오기
                Log.e("TAG", eventDay.calendar.time.date.toString())
                Log.e("TAG", "한번 더")
            }
        }
    }


}
