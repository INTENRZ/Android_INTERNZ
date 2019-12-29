package com.example.internz.ui.story

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.story.StoryData
import com.example.internz.data.story.StoryDataTemporal
import com.example.internz.data.story.StoryDataTemporal2
import com.example.internz.ui.story.detailstory.DetailStoryActivity
import com.google.android.material.tabs.TabLayout

//TODO! StoryFragment 변경해야 함

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

        adapter.data = StoryDataTemporal().getStory()
        adapter.notifyDataSetChanged()

        //사용자가 선택한 탭
        view.findViewById<TabLayout>(R.id.tabStory).addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(p0: TabLayout.Tab?) {
//                    selectedTab = p0?.text.toString()
//
//                    //TODO! 서버와 통신
//                    adapter.data = StoryDataTemporal2().getStory()
//                    adapter.notifyDataSetChanged()
                }

                override fun onTabReselected(p0: TabLayout.Tab?) {
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {
                }
            }
        )
    }
}