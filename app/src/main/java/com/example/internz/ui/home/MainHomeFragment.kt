package com.example.internz.ui.home


import android.graphics.Insets.add
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.viewpager.widget.PagerAdapter
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.BaseResponse
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.home.HomeResponseData
import com.example.internz.feature.homestory.HomestoryAdapter
import com.example.internz.feature.homecustomnotification.CustomNotificationAdapter
import com.example.internz.feature.homecustomnotification.FirstFragment
import com.example.internz.feature.homecustomnotification.SecondFragment
import com.example.internz.feature.homecustomnotification.ThirdFragment
import com.example.internz.feature.homerecomm.HomerecommAdapter
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import retrofit2.Call
import me.relex.circleindicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_main_home.viewpager
import kotlinx.android.synthetic.main.fragment_main_home.*


class MainHomeFragment : Fragment() {
    private lateinit var rv_recomm_profile: RecyclerView
    private lateinit var adapter_recomm_profile: HomerecommAdapter

    private lateinit var rv_home_story: RecyclerView
    private lateinit var adapter_homestory: HomestoryAdapter

    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // 하단 탭에 필요한 코드
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // Inflate the layout for this fragment
        val view = inflater.inflate(com.example.internz.R.layout.fragment_main_home, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /* 추천 프로필 리사이클러뷰 세팅 */
        rv_recomm_profile = view!!.findViewById(com.example.internz.R.id.rv_home_recommProfile)
        adapter_recomm_profile = HomerecommAdapter(context!!)
        rv_recomm_profile.adapter = adapter_recomm_profile
        rv_recomm_profile.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //apply SnapHelper
        val snapHelper = GravitySnapHelper(Gravity.START)
        snapHelper.attachToRecyclerView(rv_recomm_profile)

        /* 오늘의 스토리 리사이클러뷰 세팅 */
        rv_home_story = view!!.findViewById(com.example.internz.R.id.rv_homestory)
        adapter_homestory = HomestoryAdapter(context!!)
        rv_home_story.adapter = adapter_homestory
        rv_home_story.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)

        responseMainHome()
        rvCustom()

    }

    /** 메인 홈 "추천 프로필", "오늘의 스토리" 서버 통신 */
    private fun responseMainHome(){
        val call: Call<BaseResponse<HomeResponseData>> = ApiServiceImpl.service.responseMainHome(ApiServiceImpl.getToken())
        call.enqueue(
            onSuccess = {
                // 메인 홈 추천 프로필에 데이터 세팅
                adapter_recomm_profile.data = it.profile
                adapter_recomm_profile.notifyDataSetChanged()

                // 메인 오늘의 스토리에 데이터 세팅
                adapter_homestory.data = it.story
                rv_home_story.overScrollMode = View.OVER_SCROLL_NEVER
                adapter_homestory.notifyDataSetChanged()


            },
            onFail = {status, message ->  toast(message)
            }
        )
    }

    private fun rvCustom() {

        val customAdapter: PagerAdapter = CustomNotificationAdapter(childFragmentManager)
        viewpager.adapter = customAdapter
        tablayout.setupWithViewPager(viewpager)
        val indicator : CircleIndicator = view!!.findViewById(R.id.indicator)
        indicator.setViewPager(viewpager)
    }
}