package com.example.internz.ui.profile.main


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.BaseResponse
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.profile.ProfileTimelineData
import com.example.internz.data.profile.TimelineRequestData
import com.example.internz.feature.jobselect.SelectHelper
import com.example.internz.feature.message.MessageActivity
import com.example.internz.ui.profile.TimelineAddActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call

class MainProfileFragment : Fragment() {

    private lateinit var rv_profile_timeline: RecyclerView
    private lateinit var adapter_profile_mainProfile: MainProfileAdapter

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 하단 탭에 필요한 코드
        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        view.findViewById<ImageView>(R.id.img_profile_massage).setOnClickListener {
            Log.e("TAG", "MainProfile To Message clicked")
            val intent = Intent(view.context, MessageActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvInit()
        floatingClick()
    }

    fun rvInit(){

        rv_profile_timeline = view!!.findViewById(R.id.rv_profile_timeline)
        adapter_profile_mainProfile = MainProfileAdapter(context!!)
        rv_profile_timeline.adapter = adapter_profile_mainProfile
        rv_profile_timeline.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        /** 프로필 타임라인 조회 서버 통신 **/
        val timelineCall: Call<BaseResponse<List<ProfileTimelineData>>> = ApiServiceImpl.service.responseProfileTimelineList(ApiServiceImpl.getToken(), TimelineRequestData(2))
        timelineCall.enqueue(
            onSuccess = {
                adapter_profile_mainProfile.data = it
                Log.d("chohee", "응 들어옴")
            },
            onFail = {status, message ->  toast(message)
                Log.d("chohee", "응 실패")
            }
        )

//        adapter_profile_mainProfile.data = listOf(
//            ProfileTimelineData(
//
//                timelineIdx = 11,
//                userIdx = 2,
//                category = "인턴",
//                title = "NAVER SNOW Jam Studio 기획/운영팀",
//                start_date = "19.01.01",
//                end_date = "19.07.01"
//            ),
//            ProfileTimelineData(
//                timelineIdx = 11,
//                userIdx = 2,
//                category = "인턴",
//                title = "SK Telecom 서포터즈 T프렌즈 2기",
//                start_date = "19.01.01",
//                end_date = "19.07.01"
//            ),
//            ProfileTimelineData(
//                timelineIdx = 11,
//                userIdx = 2,
//                category = "인턴",
//                title = "SOPT 25기 기획팀 ",
//                start_date = "19.01.01",
//                end_date = "19.07.01"
//            ),
//            ProfileTimelineData(
//                timelineIdx = 11,
//                userIdx = 2,
//                category = "인턴",
//                title = "SK Telecom 서포터즈 T프렌즈 2기",
//                start_date = "19.01.01",
//                end_date = "19.07.01"
//            ),
//            ProfileTimelineData(
//                timelineIdx = 11,
//                userIdx = 2,
//                category = "인턴",
//                title = "인턴즈짱",
//                start_date = "19.01.01",
//                end_date = "19.07.01"
//            ),
//            ProfileTimelineData(
//                timelineIdx = 11,
//                userIdx = 2,
//                category = "인턴",
//                title = "리사이클러뷰 임",
//                start_date = "19.01.01",
//                end_date = "19.07.01"
//            ),
//            ProfileTimelineData(
//                timelineIdx = 11,
//                userIdx = 2,
//                category = "인턴",
//                title = "타임라인임~",
//                start_date = "19.01.01",
//                end_date = "19.07.01"
//            )
//        )
        adapter_profile_mainProfile.notifyDataSetChanged()
    }

    fun floatingClick(){
        img_profile_floating.setOnClickListener{
            // 타임라인 추가 도중 뒤로가기 눌렀을 때 count 값 초기화
            SelectHelper.categoryCount = 0

            val intent = Intent(context, TimelineAddActivity::class.java)
            startActivity(intent)
        }
    }
}