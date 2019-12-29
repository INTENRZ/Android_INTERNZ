package com.example.internz.ui.notification


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.internz.R
import com.example.internz.data.notification.NotificationListData
import com.example.internz.ui.calendar.CalendarFragment
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_notification_list.*

class NotificationFragment : Fragment() {
    private lateinit var rvNotificationList: RecyclerView
    private lateinit var notificationListAdapter : NotificationListAdapter

    private lateinit var notificationViewModel: NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // 하단 탭에 필요한 코드
        notificationViewModel =
            ViewModelProviders.of(this).get(NotificationViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_notification_list, container, false)

        makeNotificationList(view)
        return view
    }

    fun makeNotificationList(view : View) {
        rvNotificationList = view!!.findViewById(R.id.rvNotilist)
        notificationListAdapter = NotificationListAdapter(context!!)

        rvNotificationList.apply {
            adapter = notificationListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        notificationListAdapter.data = listOf(
            NotificationListData(
                img = R.drawable.apr_corp,
                title = "에이피알",
                desc = "마케팅 콘텐츠 디자인",
                dday = "D-13"
            ),
            NotificationListData(
                img = R.drawable.apr_corp,
                title = "한국언론진흥재단",
                desc = "광고, 일반행정",
                dday = "D-13"
            ),
            NotificationListData(
                img = R.drawable.apr_corp,
                title = "라인스튜디오",
                desc = "모바일 게임 데이터 분석",
                dday = "D-13"
            ),
            NotificationListData(
                img = R.drawable.apr_corp,
                title = "쇼박스",
                desc = "마케팅팀",
                dday = "13"
            ),
            NotificationListData(
                img = R.drawable.apr_corp,
                title = "에이피알",
                desc = "마케팅 콘텐츠 디자인",
                dday = "D-13"
            ),
            NotificationListData(
                img = R.drawable.apr_corp,
                title = "에이피알",
                desc = "마케팅 콘텐츠 디자인",
                dday = "D-13"
            ),
            NotificationListData(
                img = R.drawable.apr_corp,
                title = "에이피알",
                desc = "마케팅 콘텐츠 디자인",
                dday = "D-13"
            ),
            NotificationListData(
                img = R.drawable.apr_corp,
                title = "에이피알",
                desc = "마케팅 콘텐츠 디자인",
                dday = "D-13"
            ),
            NotificationListData(
                img = R.drawable.apr_corp,
                title = "에이피알",
                desc = "마케팅 콘텐츠 디자인",
                dday = "D-13"
            ),
            NotificationListData(
                img = R.drawable.apr_corp,
                title = "에이피알",
                desc = "마케팅 콘텐츠 디자인",
                dday = "D-13"
            ),
            NotificationListData(
                img = R.drawable.apr_corp,
                title = "에이피알",
                desc = "마케팅 콘텐츠 디자인",
                dday = "D-13"
            )

        )

        notificationListAdapter.notifyDataSetChanged()

        //공고 -> 캘린더 이동 imageview click listener
        view.findViewById<ImageView>(R.id.imgNotiToCalendar).setOnClickListener {
            Log.e("TAG", "버튼이 눌렸습니다.")
            //TODO! fragment attach, detach, destroy..?
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, CalendarFragment())?.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e("TAG", "notification_list_fragment 파괴됨")
    }

    override fun onDetach() {
        super.onDetach()

        Log.e("TAG", "notification_list_fragment 완전히 파괴됨")
    }
}


