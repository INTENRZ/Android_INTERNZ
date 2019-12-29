package com.example.internz.feature.followinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.follow.FollowerData
import com.example.internz.data.follow.FollowingData

class FollowingListActivity : AppCompatActivity() {

    private lateinit var rvFollowingList: RecyclerView
    private lateinit var followingListAdapter: FollowingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_following_list)

        rvFollowingList = findViewById(R.id.rvFollowing)
        followingListAdapter = FollowingListAdapter(this)

        rvFollowingList.apply {
            adapter = followingListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        followingListAdapter.data = listOf(
            FollowingData(
                profileImg = R.drawable.apr_corp,
                name = "오현택",
                desc = "서비스 기획과 리서치를 좋아하는 UX/UI 디자이너 입니다."
            ),
            FollowingData(
                profileImg = R.drawable.apr_corp,
                name = "오현택",
                desc = "서비스 기획과 리서치를 좋아하는 UX/UI 디자이너 입니다."
            ),
            FollowingData(
                profileImg = R.drawable.apr_corp,
                name = "오현택",
                desc = "서비스 기획과 리서치를 좋아하는 UX/UI 디자이너 입니다."
            )

        )

        followingListAdapter.notifyDataSetChanged()

    }
}







