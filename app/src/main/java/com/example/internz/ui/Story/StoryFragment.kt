package com.example.internz.ui.Story


import android.os.Bundle
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
import com.example.internz.ui.home.HomeAdapter

class StoryFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: StoryAdapter

    private lateinit var adapter_recomm_profile: HomeAdapter
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
        val spinner = view.findViewById<Spinner>(R.id.spinnerStory)
        val adapter = ArrayAdapter.createFromResource(view.context, R.array.spinner, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}