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
import com.example.internz.common.enqueue
import com.example.internz.data.story.StoryCategoryRequestData
import com.example.internz.data.story.StoryCategoryResponseData
import com.google.android.material.tabs.TabLayout


class StoryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : StoryAdapter
    private lateinit var storyViewModel: StoryViewModel

    //사용자가 선택한 탭
    private var selectedTab : String = "전체"
    private var selectedSort : String = "0"

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
        requestServerBroadcast(selectedTab, selectedSort)

        //사용자가 선택한 탭
        view.findViewById<TabLayout>(R.id.tabStory).addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(p0: TabLayout.Tab?) {
                    selectedTab = p0?.text.toString()

                    Log.e("TAG", "selectedTab : ${selectedTab}, selectedSort : ${selectedSort}")
                    requestServerBroadcast(selectedTab, selectedSort)
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
                    selectedSort = position.toString()

                    Log.e("TAG", "selectedTab : ${selectedTab}, selectedSort : ${selectedSort}")
                    requestServerBroadcast(selectedTab, selectedSort)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
    }

    private fun requestServerBroadcast(category : String, sort : String) {
        val call = ApiServiceImpl.service.requestCategoryStory(
            StoryCategoryRequestData(
                category, sort
            )
        )

        call.enqueue(
            onSuccess = {

                adapter.data = it
                adapter.notifyDataSetChanged()

            },
            onFail = {
                status, message ->
                    run {
                        //데이터가 없는 경우 recycler view data 초기화
                        adapter.data = emptyList()
                        adapter.notifyDataSetChanged()
                }
            }
        )
    }
}