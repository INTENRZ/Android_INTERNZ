package com.example.internz.feature.story

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.data.story.StoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//TODO! 서버 -> 클라이언트 데이터 받아와야 함

class StoryActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: StoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        storyFunction()
    }

    private fun storyFunction() {
        //변수 초기화
        recyclerView = findViewById(R.id.rvStory)
        adapter = StoryAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //서버 통신
        val call = ApiServiceImpl.service.requestStory()

        call.enqueue(
            object : Callback<List<StoryData>> {
                override fun onFailure(call: Call<List<StoryData>>, t: Throwable) {
                    Log.e("TAG", "StoryActivity 서버 통신 불가")
                }

                override fun onResponse(
                    call: Call<List<StoryData>>,
                    response: Response<List<StoryData>>
                ) {
                    if(response.isSuccessful) {
                        adapter.data = response.body()!!
                        adapter.notifyDataSetChanged()
                    }
                    else {
                        //TODO! 서버에서 보내주는 메시지 띄우기
                    }
                }
            }
        )
    }
}
