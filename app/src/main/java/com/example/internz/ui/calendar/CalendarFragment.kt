package com.example.internz.ui.calendar


import android.icu.util.Calendar
import androidx.navigation.findNavController
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.example.internz.R
import com.example.internz.common.toast
import com.example.internz.data.calendar.CalendarDataTemporal
import com.example.internz.ui.notification.NotificationFragment

/**
 * A simple [Fragment] subclass.
 */
class CalendarFragment : Fragment() {
    private lateinit var calendarFragment: CalendarFragment
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : CalendarAdapter

    //달력
    private lateinit var calendar : java.util.Calendar
    private lateinit var calendarView: CalendarView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val calendarViewModel = ViewModelProviders.of(this).get(CalendarViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        calendarFunction(view)
        return view
    }

    private fun calendarFunction(view : View) {
        calendarFragment = CalendarFragment()

        //RecyclerView setting
        recyclerView = view.findViewById(R.id.rvCalendar)
        adapter = CalendarAdapter(view.context)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        adapter.data = CalendarDataTemporal().getCalendarData()
        adapter.notifyDataSetChanged()

        //스크롤 이펙트 제거
        view.findViewById<RecyclerView>(R.id.rvCalendar).overScrollMode = View.OVER_SCROLL_NEVER

        //Calendar Setting
        calendarView = view.findViewById(R.id.Calendar)
        var events = listOf<EventDay>()

        //image setting
        calendarView.setForwardButtonImage(view.resources.getDrawable(R.drawable.notice_calendar_next_ic))
        calendarView.setPreviousButtonImage(view.resources.getDrawable(R.drawable.notice_calendar_before_ic))

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

        //calendar -> 공고 이동 imageview click listener
        view.findViewById<ImageView>(R.id.imgCalendarToNoti).setOnClickListener {
//            activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.frag_navigation_notice) //이것보단 밑에 코드가 나은듯
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, NotificationFragment())?.commit()
            Log.e("TAG", "캘린더 -> 공고 fragment 교체됨")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e("TAG", "CalendarFragment 파괴됨")
    }

    override fun onDetach() {
        super.onDetach()

        Log.e("TAG", "CalendarFragment 완전히 파괴됨")
    }
}
