package com.example.internz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.data.NotificationListData
import com.example.internz.notification.NotificationListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var rvNotificationList: RecyclerView
    private lateinit var notificationListAdapter : NotificationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeNotificationList()
        makeSpinner()
    }

    fun makeSpinner() {
        val items = resources.getStringArray(R.array.list)
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        txtNotilistfilter.adapter = myAdapter

        txtNotilistfilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when(position) {
                    0   ->  {

                    }
                    1   ->  {

                    }
                    //...
                    else -> {

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    fun makeNotificationList() {
        rvNotificationList = findViewById(R.id.rvNotilist)
        notificationListAdapter = NotificationListAdapter(this)

        rvNotificationList.apply {
            adapter = notificationListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        notificationListAdapter.data = listOf(
            NotificationListData(img = R.drawable.apr_corp, title = "에이피알" , desc = "마케팅 콘텐츠 디자인", dday = "D-13"),
            NotificationListData(img = R.drawable.korea_press_foundation, title = "한국언론진흥재단" , desc = "광고, 일반행정", dday = "D-13"),
            NotificationListData(img = R.drawable.line, title = "라인스튜디오" , desc = "모바일 게임 데이터 분석", dday = "D-13"),
            NotificationListData(img = R.drawable.showbox, title = "쇼박스" , desc = "마케팅팀", dday = "13"),
            NotificationListData(img = R.drawable.apr_corp, title = "에이피알" , desc = "마케팅 콘텐츠 디자인", dday = "D-13"),
            NotificationListData(img = R.drawable.apr_corp, title = "에이피알" , desc = "마케팅 콘텐츠 디자인", dday = "D-13"),
            NotificationListData(img = R.drawable.apr_corp, title = "에이피알" , desc = "마케팅 콘텐츠 디자인", dday = "D-13"),
            NotificationListData(img = R.drawable.apr_corp, title = "에이피알" , desc = "마케팅 콘텐츠 디자인", dday = "D-13"),
            NotificationListData(img = R.drawable.apr_corp, title = "에이피알" , desc = "마케팅 콘텐츠 디자인", dday = "D-13"),
            NotificationListData(img = R.drawable.apr_corp, title = "에이피알" , desc = "마케팅 콘텐츠 디자인", dday = "D-13"),
            NotificationListData(img = R.drawable.apr_corp, title = "에이피알" , desc = "마케팅 콘텐츠 디자인", dday = "D-13")

        )

        notificationListAdapter.notifyDataSetChanged()
    }
}
