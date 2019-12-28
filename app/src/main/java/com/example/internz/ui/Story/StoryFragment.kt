package com.example.internz.ui.Story


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.story.StoryDataTemporal
import com.example.internz.data.story.StoryDataTemporal2
import com.google.android.material.tabs.TabLayout

/**
 * TODO! 필터 최신순, 조회순 기능 추가해야 함
 */

class StoryFragment : Fragment() {
    //for RecyclerView
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: StoryAdapter
    //for TabLayout
    private lateinit var tabLayout : TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_story, container, false)

        storyFunction(view)
        return view
    }

    private fun storyFunction(view : View) {
        //변수 초기화
        recyclerView = view.findViewById(R.id.rvStory)
        adapter = StoryAdapter(view.context)

        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter

        //TODO! 서버 통신시에는 40,41번 코드 제거후 아래 call 주석 풀기
        adapter.data = StoryDataTemporal().getStory()
        adapter.notifyDataSetChanged()

        //1. 사용자의 tab 선택에 따른 fragment 제공
        //전체 fragment add

        tabLayout = view.findViewById(R.id.tabStory)
        tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(p0: TabLayout.Tab?) {
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position) {
                        0 -> {
                            //전체
                            adapter.data = StoryDataTemporal().getStory()
                            adapter.notifyDataSetChanged()
//                            getDataFromServer(view, tab?.text.toString())
                            Log.e("TAG", "StoryFragment : ${tab?.text.toString()}")
                        }
                        1 -> {
                            //인턴
                            adapter.data = StoryDataTemporal2().getStory()
                            adapter.notifyDataSetChanged()
//                            getDataFromServer(view, tab?.text.toString())
                        }
                        2 -> {
                            //대외활동
                            getDataFromServer(view, tab?.text.toString())
                        }
                        3 -> {
                            //공모전
                            getDataFromServer(view, tab?.text.toString())
                        }
                        4 -> {
                            //동아리
                            getDataFromServer(view, tab?.text.toString())
                        }
                        5 -> {
                            //자격증
                            getDataFromServer(view, tab?.text.toString())
                        }
                        6 -> {
                            //기타
                            getDataFromServer(view, tab?.text.toString())
                        }
                    }
                }
        })

        //2. 사용자의 정렬 선택에 따라 다른 fragment show


        //서버통신요청
//        getDataFromServer(view)

        //스피너 설정
        val spinner = view.findViewById<Spinner>(R.id.spinnerStory)
        val adapter = ArrayAdapter.createFromResource(view.context, R.array.spinner, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun getDataFromServer(view : View, tab : String) {
        //TODO! ApiService 구현하고 주석 제거
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
    }
}