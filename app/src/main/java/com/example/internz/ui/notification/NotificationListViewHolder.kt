package com.example.internz.ui.notification

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.data.notification.NotificationResponseData
import com.example.internz.feature.web.WebViewActivity
import retrofit2.http.Url
import java.net.URL

class NotificationListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val view : View = view.findViewById(R.id.content) //recycler item view
    val rightView : View = view.findViewById(R.id.right) //swipe view

    var jobIdx : Int? = null
    val photo : ImageView = view.findViewById(R.id.imgNotilistImg)
    val title : TextView = view.findViewById(R.id.txtNotilistTitle)
    val dday : TextView = view.findViewById(R.id.txtNotilistDday)
    val desc : TextView = view.findViewById(R.id.txtNotilistDesc)

    fun bind(data : NotificationResponseData) {
        //TODO! 이미지 가운데 정렬 후 높이 맞춰야 함
        Glide
            .with(view.context)
            .load(data.logo)
            .into(photo)

        title.text = data.company
        //날짜 처리
        if(data.day < 0) {
            dday.text = "D" + data.day.toString()
        } else {
            dday.text = "D+" + data.day.toString()
        }

        desc.text = data.team
        jobIdx = 5 //TODO! 캘린더 추가시 필요한 jobIdx 수정 필요

        //recyclerview 선택시의 click event
        view.setOnClickListener {
            val intent = Intent(view.context, WebViewActivity::class.java)
                .putExtra("url", data.url)

            view.context.startActivity(intent)
        }

        //스와이프 후 공고 캘린더에 추가
        rightView.setOnClickListener {
            val call = ApiServiceImpl.service.requestAddNotification(
                jobIdx.toString(), //TODO! jobIdx를 어떻게하면 가장 효과적으로 받아올 수 있을까?
                ApiServiceImpl.getToken()
            )

            call.enqueue(
                onSuccess = {
                    if (it.success.toString().equals("true")) {
                        Toast.makeText(view.context, "${desc.text}의 공고가 캘린더에 추가되었습니다.", Toast.LENGTH_SHORT).show()
                        Log.e("TAG", "NotificationListViewHolder : onSuccess 메서드 실행됨")
                    }
                },
                onFail = {
                    status, message -> Log.e("TAG", "NotificationListViewHolder : onFail 메서드 실행됨")
                }
            )
        }
    }
}