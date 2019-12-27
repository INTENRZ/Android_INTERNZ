package com.example.internz.feature.story

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.data.story.StoryData
import com.example.internz.data.story.StoryDataTemporal
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

        //TODO! 서버 통신시에는 40,41번 코드 제거후 아래 call 주석 풀기
        adapter.data = StoryDataTemporal().getStory()
        adapter.notifyDataSetChanged()

        //TODO! ApiService 구현하고 주석 풀기
        /*
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
         */

        //스피너 설정
        val spinner = findViewById<Spinner>(R.id.spinnerStory)
        val adapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

    }
}
