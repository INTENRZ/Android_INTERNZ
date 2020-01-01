package com.example.internz.feature.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.data.comment.CommentDataTemporal
import com.example.internz.data.comment.CommentRequestData
import com.example.internz.ui.story.StoryHelper
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

        //서버 통신
        getComment()

        //타임라인 delete click event
        imgCommentDelete?.setOnClickListener {
            finish()
        }

        //댓글 작성
        imgCommentSend?.setOnClickListener {
            if(edtCommentMake.text.isNotEmpty()) {
                val call = ApiServiceImpl.service.requestMakeComment(
                    ApiServiceImpl.getToken(),
                    StoryHelper.getStoryIndex(),
                    CommentRequestData(edtCommentMake.text.toString())
                )

                Log.e("TAG", "CommentActivity : ${StoryHelper.getStoryIndex()}, ${ApiServiceImpl.getToken()}}")

                call.enqueue(
                    onSuccess = {
                        //내가 보낸 댓글 서버로 전송
                        adapter.data = it
                        adapter.notifyDataSetChanged()

                        //댓글 리스트도 갱신
                        getComment()
                        Log.e("TAG", "CommentActivity : onSuccess 메서드 실행됨")

                        edtCommentMake.text.clear()
                    },
                    onFail = { status, message ->
                        Log.e("TAG", "CommentActivity : onFail 메서드 실행됨, ${message}")
                    }
                )
            } else {
                Toast.makeText(applicationContext, "내용을 입력하세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getComment() {
        //TODO! 서버로 데이터 가져오기
        val call = ApiServiceImpl.service.requestComment(
            StoryHelper.getStoryIndex()
        )
        Log.e("TAG", "CommentActivity의 storyindex : ${StoryHelper.getStoryIndex()}")

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
    }
}
