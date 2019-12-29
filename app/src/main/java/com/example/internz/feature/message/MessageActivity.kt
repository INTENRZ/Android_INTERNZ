package com.example.internz.feature.message

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.messagelist.MessageDataTemp
import kotlinx.android.synthetic.main.fragment_profile.*

class MessageActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        messageFunction()
    }

    private fun messageFunction() {
        recyclerView = findViewById(R.id.rvMessage)
        adapter = MessageAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.data = MessageDataTemp().getMessage()
        adapter.notifyDataSetChanged()

        //쪽지 보내기
        img_profile_massage.setOnClickListener {
            val intent = Intent(this, MessageActivity::class.java)
            startActivity(intent)
        }
    }
}
