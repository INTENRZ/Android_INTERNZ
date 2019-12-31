package com.example.internz.ui.profile.main


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import com.example.internz.data.UserIdxRequestData
import com.example.internz.feature.SelectHelper
import com.example.internz.feature.message.MessageActivity
import com.example.internz.ui.profile.TimelineAddActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call

class MainProfileFragment : Fragment() {

    companion object {
        const val TIMELINE_ADD_SUCCESS = 1
    }

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

//        val nickname = view.findViewById<TextView>(R.id.txt_profile_name)
//        val imgFace = view.findViewById<ImageView>(R.id.img_profile_face)
//        val followerNum = view.findViewById<TextView>(R.id.txt_profile_follower_number)
//        val followingNum = view.findViewById<TextView>(R.id.txt_profile_following_number)
//        val introduce = view.findViewById<TextView>(R.id.txt_profile_intoduce)
//        val job1 = view.findViewById<TextView>(R.id.txt_job1)
//        val job2 = view.findViewById<TextView>(R.id.txt_job2)
//        val job3 = view.findViewById<TextView>(R.id.txt_job3)


//        /* 프로필 정보 서버 요청 */
//        val profileCall: Call<BaseResponse<ProfileTimelineData>> = ApiServiceImpl.service.requestProfile(ApiServiceImpl.getToken(), UserIdxRequestData(77))
//        profileCall.enqueue(
//            onSuccess = {
//               Log.d("chohee", "통신성공")
//            },
//            onFail = {status, message ->  toast(message)
//            }
//        )
        /* 프로필 정보 뷰 객체 선언 및 초기화 */


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvInit()
        floatingClick()
    }

    /* 타임라인 추가 후 새로고침 */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == TIMELINE_ADD_SUCCESS){
            if(resultCode == Activity.RESULT_OK){
                /*프로필 조회 새로고침용 서버 요청*/
                val timelineCall: Call<BaseResponse<List<ProfileTimelineData>>> = ApiServiceImpl.service.responseProfileTimelineList(ApiServiceImpl.getToken(),
                    UserIdxRequestData(77)
                )
                timelineCall.enqueue(
                    onSuccess = {
                        adapter_profile_mainProfile.data = it
                        adapter_profile_mainProfile.notifyDataSetChanged()
                    },
                    onFail = {status, message ->  toast(message)
                    }
                )

            }
        }
    }

    fun rvInit(){

        rv_profile_timeline = view!!.findViewById(R.id.rv_profile_timeline)
        adapter_profile_mainProfile = MainProfileAdapter(context!!)
        rv_profile_timeline.adapter = adapter_profile_mainProfile
        rv_profile_timeline.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        /** 프로필 타임라인 조회 서버 통신 **/
        // 여기 setUserId로 2자리에 넣어야함
        val timelineCall: Call<BaseResponse<List<ProfileTimelineData>>> = ApiServiceImpl.service.responseProfileTimelineList(ApiServiceImpl.getToken(),
            UserIdxRequestData(77)
        )
        timelineCall.enqueue(
            onSuccess = {
                adapter_profile_mainProfile.data = it
            },
            onFail = {status, message ->  toast(message)
            }
        )
        adapter_profile_mainProfile.notifyDataSetChanged()

    }


    fun floatingClick(){
        img_profile_floating.setOnClickListener{
            // 타임라인 추가 도중 뒤로가기 눌렀을 때 count 값 초기화
            SelectHelper.categoryCount = 0

            val intent = Intent(context, TimelineAddActivity::class.java)
            startActivityForResult(intent, TIMELINE_ADD_SUCCESS)
        }
    }
}