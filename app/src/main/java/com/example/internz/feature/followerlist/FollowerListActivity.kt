package com.example.internz.feature.followerlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.common.toast

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

        val followerCall = ApiServiceImpl.service.requestFollwer(ApiServiceImpl.getToken())


        followerCall.enqueue(

            onSuccess = {
                followerListAdapter.data = it
                followerListAdapter.notifyDataSetChanged()
            },

            onFail = {status, message -> toast(message)  }
        )

    }
}

