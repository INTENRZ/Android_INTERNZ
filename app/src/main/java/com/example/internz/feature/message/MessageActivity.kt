package com.example.internz.feature.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.messagelist.MessageDataTemp
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : AppCompatActivity() {
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

        adapter.data = MessageDataTemp().getMessage()
        adapter.notifyDataSetChanged()

        //뒤로가기(back button)
        imgSendBack.setOnClickListener {
            this.finish()
        }
    }
}
