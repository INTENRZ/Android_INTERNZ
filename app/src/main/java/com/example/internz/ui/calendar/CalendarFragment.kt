package com.example.internz.ui.calendar


import android.icu.util.Calendar
import android.os.Bundle
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
import com.example.internz.R
import com.example.internz.common.toast
import com.example.internz.data.calendar.CalendarDataTemporal


/**
 * A simple [Fragment] subclass.
 */
class CalendarFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : CalendarAdapter

    //달력
    private lateinit var events : List<EventDay>
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

        //Calendar Setting
        calendar = java.util.Calendar.getInstance()
        calendarView = view.findViewById(R.id.calendarCalendar)
        events = ArrayList<EventDay>()

        //add one events
        calendar.add(java.util.Calendar.DAY_OF_MONTH, 1) //1일에 이벤트 추가
        //TODO! 오류 확인 및 코드 교체
//        events += listOf(EventDay(calendar, view.context.getDrawable(R.drawable.round_for_calendar)))
        events += listOf<EventDay>(EventDay(calendar, R.drawable.round_for_calendar))

        calendar.add(java.util.Calendar.DAY_OF_MONTH, 10)
        events += listOf<EventDay>(EventDay(calendar, R.drawable.round_for_calendar))

        //event setting
        calendarView.setEvents(events)

        //set click listener for calendar
        calendarView.setOnDayClickListener {
            Toast.makeText(view.context, calendarView.selectedDates.toString(), Toast.LENGTH_SHORT).show()
        }

    }
}
