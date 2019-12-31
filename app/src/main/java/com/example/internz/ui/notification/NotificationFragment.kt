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
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.notification.NotificationResponseData
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
                        Toast.makeText(context, "최신순 공고를 조회합니다.", Toast.LENGTH_SHORT).show()

                    }
                    1   ->  {
                        Toast.makeText(context, "조회순 공고를 조회합니다.", Toast.LENGTH_SHORT).show()
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

        val call = ApiServiceImpl.service.requestAllNotification()

        call.enqueue(
            onSuccess = {
                notificationListAdapter.data = it
                notificationListAdapter.notifyDataSetChanged()
            },
            onFail = {
                status, message -> toast(message)
            }
        )

        notificationListAdapter.notifyDataSetChanged()

        //공고 -> 캘린더 이동 imageview click listener
        activity?.findViewById<ImageView>(R.id.imgNotiToCalendar)?.setOnClickListener {
            startActivity(Intent(context, CalendarActivity::class.java))
        }

        //필터 click event
        imgNotiFilter?.setOnClickListener {
            val intent = Intent(context, FilterActivity::class.java)
            startActivity(intent)
        }
    }
}