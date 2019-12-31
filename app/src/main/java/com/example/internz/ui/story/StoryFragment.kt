package com.example.internz.ui.story

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.data.story.StoryDataTemporal
import com.example.internz.data.story.StoryDataTemporal2
import com.google.android.material.tabs.TabLayout


class StoryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : StoryAdapter
    private lateinit var storyViewModel: StoryViewModel

    //사용자가 선택한 탭
    private var selectedTab : String = "전체"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //TODO! 이 코드 삭제하면 에러나는지 확인
        storyViewModel = ViewModelProviders.of(this).get(StoryViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_story, container, false)

        storyFunction(view)
        return view
    }

    private fun storyFunction(view : View) {
        val spinner = view.findViewById<Spinner>(R.id.spinnerStory)
        val arrayAdapter = ArrayAdapter.createFromResource(view.context, R.array.spinner, android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        //스크롤 이펙 제거
        view.findViewById<RecyclerView>(R.id.rvStory).overScrollMode = View.OVER_SCROLL_NEVER

        //변수 초기화
        recyclerView = view.findViewById(R.id.rvStory)
        adapter = StoryAdapter(view.context)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        //하단탭을 눌렀을때 기본으로 보여질 데이터 : 전체 + 최신순
        //TODO!

//        adapter.data = StoryDataTemporal().getStory()
        adapter.notifyDataSetChanged()

        //사용자가 선택한 탭
        view.findViewById<TabLayout>(R.id.tabStory).addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(p0: TabLayout.Tab?) {
                    when(p0?.text) {
                        "전체" -> {

                        }
                        "인턴" -> {

                        }
                        "대외활동" -> {

                        }
                        "공모전" -> {

                        }
                        "동아리" -> {

                        }
                        "자격증" -> {

                        }
                        "기타" -> {

                        }
                    }



                    selectedTab = p0?.text.toString()

                    //TODO! 서버와 통신
                    adapter.data = StoryDataTemporal2().getStory()
                    adapter.notifyDataSetChanged()
                }

                override fun onTabReselected(p0: TabLayout.Tab?) {
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {
                }
            }
        )

        //스피너 기능 정의
        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when(position) {
                        0 -> {
                            //최신순 TODO! 서버요청
                            Log.e("TAG", "StoryFragment의 최신순 조회")
                        }
                        1 -> {
                            //조회순 TODO! 서버요청
                            Log.e("TAG", "StoryFragment의 조회순 조회")
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
    }
}