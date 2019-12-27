package com.example.internz.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.home.RecommData
import com.example.internz.data.home.StoryData
import com.example.internz.data.notification.NotificationListData
import com.example.internz.feature.homestory.HomestoryAdapter

class HomeFragment : Fragment() {

    private lateinit var rv_recomm_profile: RecyclerView
    private lateinit var adapter_recomm_profile: HomeAdapter

    private lateinit var rv_home_story: RecyclerView
    private lateinit var apdater_homestory: HomestoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvInit()
        rvStory()
    }

    /* home 화면 "추천 프로필" 리사이클러뷰 init */
    fun rvInit(){
        rv_recomm_profile = activity!!.findViewById(R.id.rv_home_recommProfile)
        adapter_recomm_profile = HomeAdapter(context!!)
        rv_recomm_profile.adapter = adapter_recomm_profile
        rv_recomm_profile.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter_recomm_profile.data = listOf(
            RecommData(
                img_thumb = "ddd",
                txt_name = "김초희",
                txt_introduce = "ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ"
            ),
            RecommData(
                img_thumb = "ddd",
                txt_name = "최은지",
                txt_introduce = "ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ"
            ),
            RecommData(
                img_thumb = "ddd",
                txt_name = "지현이",
                txt_introduce = "ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ"
            ),
            RecommData(
                img_thumb = "ddd",
                txt_name = "윤주연",
                txt_introduce = "ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇ"
            )
        )
        adapter_recomm_profile.notifyDataSetChanged()
    }

    fun rvStory() {

        rv_home_story = activity!!.findViewById(R.id.rv_homestory)
        apdater_homestory = HomestoryAdapter(context!!)
        rv_home_story.adapter = apdater_homestory
        rv_home_story.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)

        apdater_homestory.data = listOf(
            StoryData(
                img = R.drawable.home_recomm_story_img,
                desc = "영화번역가는 AI 때문에 사라질 직업인가"
            ),
            StoryData(
                img = R.drawable.home_recomm_story_img,
                desc = "코딩 테스트부터 코딩 인턴까지 코딩에 대한 A to Z"
            ),
            StoryData(
                img = R.drawable.home_recomm_story3_img,
                desc = "디자인 인턴 합격까지 과정! 서류부터 면접까지!"
            ),
            StoryData(
                img = R.drawable.home_recomm_story4_img,
                desc = "비 전공자가 알아본 외국계 디자인 인턴과정"
            )
        )

        apdater_homestory.notifyDataSetChanged()
    }
}
