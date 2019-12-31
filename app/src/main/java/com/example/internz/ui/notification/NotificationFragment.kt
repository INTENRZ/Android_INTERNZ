package com.example.internz.ui.notification


import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.internz.R
import com.example.internz.data.notification.NotificationListData
import com.example.internz.feature.calendar.CalendarActivity
import com.example.internz.feature.filter.FilterActivity
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


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        makeNotificationList()
        makeSpinner()
    }

    fun makeSpinner() {

        val spinner = view?.findViewById<Spinner>(R.id.notificationSpinner)
        val arrayAdapter = ArrayAdapter.createFromResource(view!!.context, R.array.spinner, android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = arrayAdapter

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when(position) {
                    0   ->  {
                        Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
                    }
                    1   ->  {
                        Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
                    }
                    //...
                    else -> {
                        Toast.makeText(context, "3", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }

    fun makeNotificationList() {
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
        activity?.findViewById<ImageView>(R.id.imgNotiToCalendar)?.setOnClickListener {
            Log.e("TAG", "버튼이 눌렸습니다.")
            //TODO! fragment attach, detach, destroy..?
            startActivity(Intent(context, CalendarActivity::class.java))
        }

        //필터 click event
        imgNotiFilter?.setOnClickListener {
            val intent = Intent(context, FilterActivity::class.java)
            startActivity(intent)
        }
    }
}