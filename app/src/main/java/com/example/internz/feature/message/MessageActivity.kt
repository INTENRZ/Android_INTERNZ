package com.example.internz.feature.message

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.internz.R
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

        adapter.data = MessageDataTemp().getMessage()
        adapter.notifyDataSetChanged()

        //swipe for refresh
        swipe = findViewById(R.id.swipeRefresh)
        swipe.setOnRefreshListener(this)

        //뒤로가기(back button)
        imgSendBack.setOnClickListener {
            this.finish()
        }
    }

    override fun onRefresh() {
        Toast.makeText(this, "새로고침할 코드가 추가되어야 합니다.", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
            swipe.isRefreshing = false

        }, 1000)
    }
}
