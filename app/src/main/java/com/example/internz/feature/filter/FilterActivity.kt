package com.example.internz.feature.filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.filter.FilterItem
import com.example.internz.ui.notification.NotificationListAdapter
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : FilterAdapter
    private lateinit var notificationAdapter : NotificationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        filterFunction()
    }

    private fun filterFunction() {
        //변수 초기화
        recyclerView = findViewById(R.id.rvFilter)
        adapter = FilterAdapter(this)
        notificationAdapter = NotificationListAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 4)

        //recyclerview 데이터 setting
        adapter.data = listOf(
            FilterItem("전체"),
            FilterItem("기획"),
            FilterItem("인사"),
            FilterItem("회계"),
            FilterItem("교육"),
            FilterItem("구매"),
            FilterItem("홍보"),
            FilterItem("광고"),
            FilterItem("국내영업"),
            FilterItem("해외영업"),
            FilterItem("영업지원"),
            FilterItem("마케팅"),
            FilterItem("서비스"),
            FilterItem("상품개발"),
            FilterItem("공정"),
            FilterItem("생산"),
            FilterItem("품질"),
            FilterItem("유통"),
            FilterItem("연구개발"),
            FilterItem("디자인"),
            FilterItem("무역"),
            FilterItem("IT개발"),
            FilterItem("공무원"),
            FilterItem("리서치"),
            FilterItem("컨설팅"),
            FilterItem("기타")
        )
        adapter.notifyDataSetChanged()

        btnFilterApply?.setOnClickListener {

            if (FilterHelper.count < 1) {
                Toast.makeText(this, "1개의 필터를 선택해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //서버 통신
            val call = ApiServiceImpl.service.requestJobFilter(FilterHelper.filterText.toString())

            call.enqueue(
                onSuccess = {
                    notificationAdapter.data = it
                    notificationAdapter.notifyDataSetChanged()

                    //FilterHelper 초기화
                    FilterHelper.count = 0
                    finish()
                },
                onFail = {
                    status, message -> {
                        Log.e("TAG", "FilterActivity : status : ${status}, message : ${message}")
                    }
                }
            )
        }

        imgFilterBack?.setOnClickListener {
            finish()
        }
    }
}
