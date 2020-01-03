package com.example.internz.feature.message.messagelist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.data.message.MessageListRequestData
import com.example.internz.data.messagelist.MessageListDataTemp
import com.example.internz.feature.message.messagesend.MessageSendActivity
import kotlinx.android.synthetic.main.activity_message_list.*

class MessageListActivity : AppCompatActivity() {
    private val REQUEST_CODE = 100

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_list)

        messageListFunction()
    }

    private fun messageListFunction() {

        //변수 초기화
        recyclerView = findViewById(R.id.rvList)
        adapter = MessageListAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        getMessageData()

        //취소 버튼
        imgListBack.setOnClickListener {
            finish()
        }

        //전송 버튼
        txtListSend.setOnClickListener {
            val intent = Intent(this, MessageSendActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    private fun getMessageData() {
        val call = ApiServiceImpl.service.requestMessageList(
            ApiServiceImpl.getToken(),
            MessageListRequestData(ApiServiceImpl.getReceiverIdx().toInt())
        )

        Log.e("TAG", "내가 서버로 보낸 receiver 인덱스 : ${ApiServiceImpl.getReceiverIdx()}")

        call.enqueue(
            onSuccess = {
                adapter.data = it
                adapter.notifyDataSetChanged()
            },
            onFail = {
                status, message -> Log.e("TAG", "MessageList FAIL : ${status}, ${message}")
            }
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            REQUEST_CODE -> {
                when(resultCode) {
                    Activity.RESULT_OK -> {
                        //목록 새로고침
                        getMessageData()
                    }
                }
            }
        }
    }
}
