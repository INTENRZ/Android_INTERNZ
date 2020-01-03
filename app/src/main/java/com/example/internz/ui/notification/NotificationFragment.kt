package com.example.internz.ui.notification


import android.app.Activity
import android.content.Intent
import android.os.Bundle
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
import com.example.internz.feature.calendar.CalendarActivity
import com.example.internz.feature.filter.FilterActivity
import com.example.internz.feature.filter.FilterHelper
import kotlinx.android.synthetic.main.fragment_notification_list.*

class NotificationFragment : Fragment() {
    private val REQUEST_CODE = 200

    private lateinit var rvNotificationList: RecyclerView
    private lateinit var notificationListAdapter : NotificationListAdapter

    private var selectedTask : String = "전체"
    private var selectedSort : String = "0"

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
        val arrayAdapter = ArrayAdapter.createFromResource(view!!.context, R.array.notiSpinner, android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = arrayAdapter

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when(position) {
                    0   ->  {
                        selectedSort = "0" //전체공고
                        requestData()
                    }
                    1   ->  {
                        selectedSort = "1" //지난공고
                        requestData()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    //하단바 선택시 제공
    fun makeNotificationList() {
        rvNotificationList = view!!.findViewById(R.id.rvNotilist)
        notificationListAdapter = NotificationListAdapter(context!!)

        rvNotificationList.apply {
            adapter = notificationListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        requestData()

        //공고 -> 캘린더 이동 imageview click listener
        activity?.findViewById<ImageView>(R.id.imgNotiToCalendar)?.setOnClickListener {
            startActivity(Intent(context, CalendarActivity::class.java))
        }

        //필터 click event
        imgNotiFilter?.setOnClickListener {
            val intent = Intent(context, FilterActivity::class.java)

            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            REQUEST_CODE -> {
                when(resultCode) {
                    Activity.RESULT_OK -> {
                        //선택된 필터
                        Log.e("TAG", "공고 리스트, 선택된 필터 : ${FilterHelper.currentButton?.text.toString()}")
                        selectedTask = FilterHelper.currentButton?.text.toString()

                        //통신
                        requestData()
                    }
                }
            }
        }
    }

    private fun requestData() {

        val call = ApiServiceImpl.service.requestNotification(
            selectedTask, selectedSort
        )

        call.enqueue(
            onSuccess = {
                notificationListAdapter.data = it
                notificationListAdapter.notifyDataSetChanged()
            },
            onFail = {
                status, message -> Log.e("TAG", "공고 데이터 통신 FAIL, ${status}, ${message}")
            }
        )
    }
}