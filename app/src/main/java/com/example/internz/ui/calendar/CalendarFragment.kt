package com.example.internz.ui.calendar


import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applandeo.materialcalendarview.EventDay
import com.example.internz.R
import com.example.internz.common.toast
import com.example.internz.data.calendar.CalendarDataTemporal
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class CalendarFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : CalendarAdapter

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
        recyclerView = view.findViewById(R.id.rvCalendar)
        adapter = CalendarAdapter(view.context)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        adapter.data = CalendarDataTemporal().getCalendarData()
        adapter.notifyDataSetChanged()

        //calendar custom area
        val events : List<EventDay> = ArrayList<EventDay>()
        val calendar : Calendar = android.icu.util.Calendar.getInstance()
//        events.add(EventDay(calendar, R.drawable.circle_shape))

        //오늘 날짜 지정

    }
}
