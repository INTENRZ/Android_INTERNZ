package com.example.internz.feature.message.messagelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.messagelist.MessageListDataTemp
import com.example.internz.feature.message.messagesend.MessageSendActivity
import kotlinx.android.synthetic.main.activity_message_list.*

class MessageListActivity : AppCompatActivity() {
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

        adapter.data = MessageListDataTemp().getMessageList()
        adapter.notifyDataSetChanged()

        //취소 버튼
        imgListBack.setOnClickListener {
            finish()
        }

        //전송 버튼
        txtListSend.setOnClickListener {
            val intent = Intent(this, MessageSendActivity::class.java)
            startActivity(intent)
        }
    }
}
