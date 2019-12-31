package com.example.internz.feature.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.data.comment.CommentDataTemporal
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : CommentAdapter
    private lateinit var storyIndex : String

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

//        storyIndex = intent.getStringExtra("storyIndex")

        //TODO! 서버로 데이터 가져오기
        val call = ApiServiceImpl.service.requestComment(
//            storyIndex
            "15"
        )

        call.enqueue(
            onSuccess = {
                adapter.data =it
                adapter.notifyDataSetChanged()
                Log.e("TAG", "CommentActivity : onSuccess 실행됨")
            },
            onFail = {
                status, message -> Log.e("TAG", "CommentActivity : onFail 메서드 실행됨")
            }
        )


        adapter.notifyDataSetChanged()

        //타임라인 delete click event
        imgCommentDelete?.setOnClickListener {
            finish()
        }

        //edtiText
        // edtCommentMake

        //댓글 작성
        imgCommentSend?.setOnClickListener {
            //TODO! 서버 통신
        }
    }
}
