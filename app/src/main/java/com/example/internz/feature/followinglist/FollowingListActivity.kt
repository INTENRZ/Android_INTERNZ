package com.example.internz.feature.followinglist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import kotlinx.android.synthetic.main.activity_detail_story.*

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

        val followingCall= ApiServiceImpl.service.requestFollwing(ApiServiceImpl.getToken())

        followingCall.enqueue(

            onSuccess = {
                followingListAdapter.data = it
                followingListAdapter.notifyDataSetChanged()

            },

            onFail = {
                status, message -> toast(message)
            }
        )

    }

}







