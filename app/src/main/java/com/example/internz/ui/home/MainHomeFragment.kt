package com.example.internz.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.BaseResponse
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.home.HomeResponseData
import com.example.internz.feature.homestory.HomestoryAdapter
import com.example.internz.feature.homecustomnotification.CustomNotificationAdapter
import com.example.internz.feature.homerecomm.HomerecommAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call

class MainHomeFragment : Fragment() {
    private lateinit var rv_recomm_profile: RecyclerView
    private lateinit var adapter_recomm_profile: HomerecommAdapter

    private lateinit var rv_home_story: RecyclerView
    private lateinit var apdater_homestory: HomestoryAdapter

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // 하단 탭에 필요한 코드
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_home, container, false)

        /* 추천 프로필 리사이클러뷰 세팅 + 통신 위한 리사이클러뷰, 어댑터 초기화 */
//        rv_recomm_profile = view!!.findViewById(R.id.rv_home_recommProfile)
//        adapter_recomm_profile = HomerecommAdapter(context!!)
//        rv_recomm_profile.adapter = adapter_recomm_profile
//        rv_recomm_profile.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /* 추천 프로필 리사이클러뷰 세팅 */
        rv_recomm_profile = view!!.findViewById(R.id.rv_home_recommProfile)
        adapter_recomm_profile = HomerecommAdapter(context!!)
        rv_recomm_profile.adapter = adapter_recomm_profile
        rv_recomm_profile.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        /* 맞춤 공고 리사이클러뷰 세팅 */

        /* 오늘의 스토리 리사이클러뷰 세팅 */
        rv_home_story = view!!.findViewById(R.id.rv_homestory)
        apdater_homestory = HomestoryAdapter(context!!)
        rv_home_story.adapter = apdater_homestory
        rv_home_story.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)

//        val customAdapter : PagerAdapter = CustomNotificationAdapter(childFragmentManager)
//        viewpager.adapter = customAdapter
//        tablayout.setupWithViewPager(viewpager)

        responseMainHome()
        rvCustom()

    }

    /** 메인 홈 "맞춤 공고", "추천 프로필", "오늘의 스토리" 서버 통신 */
    fun responseMainHome(){
        val call: Call<BaseResponse<HomeResponseData>> = ApiServiceImpl.service.responseMainHome(ApiServiceImpl.getToken())
        call.enqueue(
            onSuccess = {
                // 메인 홈 추천 프로필에 데이터 세팅
                adapter_recomm_profile.data = it.profile
                adapter_recomm_profile.notifyDataSetChanged()

                // 메인 오늘의 스토리에 데이터 세팅
                apdater_homestory.data = it.story
                rv_home_story.overScrollMode = View.OVER_SCROLL_NEVER
                apdater_homestory.notifyDataSetChanged()

                // 메인 맞춤 공고에 데이터 세팅

            },
            onFail = {status, message ->  toast(message)
            }
        )
    }

    fun rvCustom() {

        val customAdapter : PagerAdapter = CustomNotificationAdapter(childFragmentManager)
        viewpager.adapter = customAdapter
        tablayout.setupWithViewPager(viewpager)

    }

    /* home 화면 "추천 프로필" 리사이클러뷰 init */
    fun rvRecommProfile(){
//        rv_recomm_profile = view!!.findViewById(R.id.rv_home_recommProfile)
        adapter_recomm_profile = HomerecommAdapter(context!!)
        rv_recomm_profile.adapter = adapter_recomm_profile
        rv_recomm_profile.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter_recomm_profile.notifyDataSetChanged()
    }

    fun rvStory() {

        rv_home_story = view!!.findViewById(R.id.rv_homestory)
        apdater_homestory = HomestoryAdapter(context!!)
        rv_home_story.adapter = apdater_homestory
        rv_home_story.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)

        rv_home_story.overScrollMode = View.OVER_SCROLL_NEVER

        apdater_homestory.notifyDataSetChanged()
    }
}