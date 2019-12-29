package com.example.internz.feature.followerlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.follow.FollowerData
import com.example.internz.data.notification.NotificationListData
import com.example.internz.ui.notification.NotificationListAdapter
import com.example.internz.ui.notification.NotificationViewModel

class FollowerListActivity : AppCompatActivity() {

    private lateinit var rvFollowerList: RecyclerView
    private lateinit var followerListAdapter: FollowerListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follower_list)

        rvFollowerList = findViewById(R.id.rvFollower)
        followerListAdapter = FollowerListAdapter(this)

        rvFollowerList.apply {
            adapter = followerListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        followerListAdapter.data = listOf(
            FollowerData(
                profileImg = R.drawable.apr_corp,
                name = "오현택",
                desc = "서비스 기획과 리서치를 좋아하는 UX/UI 디자이너 입니다."
            ),
            FollowerData(
                profileImg = R.drawable.apr_corp,
                name = "오현택",
                desc = "서비스 기획과 리서치를 좋아하는 UX/UI 디자이너 입니다."
                ),
            FollowerData(
                profileImg = R.drawable.apr_corp,
                name = "오현택",
                desc = "서비스 기획과 리서치를 좋아하는 UX/UI 디자이너 입니다."
                )

        )


            followerListAdapter.notifyDataSetChanged()

    }
}

