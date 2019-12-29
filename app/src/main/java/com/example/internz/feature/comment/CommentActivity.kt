package com.example.internz.feature.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.comment.CommentDataTemporal
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        commentFunction()
    }

    private fun commentFunction() {
        //변수 초기화
        recyclerView = findViewById(R.id.rvComment)
        adapter = CommentAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //TODO! 서버로 데이터 가져오기
        adapter.data = CommentDataTemporal().getCommentData()
        adapter.notifyDataSetChanged()


        imgCommentBack.setOnClickListener {
            finish()
        }
    }
}
