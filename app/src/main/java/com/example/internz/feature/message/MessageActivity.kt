package com.example.internz.feature.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.data.messagelist.MessageDataTemp
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var swipe : SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        messageFunction()
    }

    private fun messageFunction() {
        recyclerView = findViewById(R.id.rvMessageItem)
        adapter = MessageAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        getMessageData()

//        adapter.data = MessageDataTemp().getMessage()
//        adapter.notifyDataSetChanged()

        //swipe for refresh
        swipe = findViewById(R.id.swipeRefresh)
        swipe.setOnRefreshListener(this)

        //뒤로가기(back button)
        imgSendBack.setOnClickListener {
            this.finish()
        }
    }

    override fun onRefresh() {
        getMessageData()

        Handler().postDelayed({
            swipe.isRefreshing = false

        }, 500)
    }

    private fun getMessageData() {
        val call = ApiServiceImpl.service.requestMessage(
            ApiServiceImpl.getToken()
        )

        call.enqueue(
            onSuccess = {
                //쪽지가 없을시 보여줄 메시지
                emptyMessage.text = ""

                adapter.data = it
                adapter.notifyDataSetChanged()
            },
            onFail = {
                status, message -> Log.e("TAG", "MessageActivity : 메시지 가져오기 FAIL ${status}, ${message}")
                run {
                    //쪽지가 없을시 보여줄 메시지
                    emptyMessage.text = "주고받은 쪽지가 없습니다."
                }
            }
        )
    }
}
