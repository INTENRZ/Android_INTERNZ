package com.example.internz.ui.profile.main


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.BaseResponse
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.profile.ProfileTimelineData
import com.example.internz.data.profile.ProfileData
import com.example.internz.feature.SelectHelper
import com.example.internz.feature.followerlist.FollowerListActivity
import com.example.internz.feature.followinglist.FollowingListActivity
import com.example.internz.feature.message.MessageActivity
import com.example.internz.ui.profile.TimelineAddActivity
import kotlinx.android.synthetic.main.activity_detail_story.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.txt_profile_black
import org.w3c.dom.Text
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

        /* 프로필 정보 뷰 객체 초기화 */
        val nickname = view.findViewById<TextView>(R.id.txt_profile_name)
        val imgFace = view.findViewById<ImageView>(R.id.img_profile_face)
        val followerNum = view.findViewById<TextView>(R.id.txt_profile_follower_number)
        val followingNum = view.findViewById<TextView>(R.id.txt_profile_following_number)
        val introduce = view.findViewById<TextView>(R.id.txt_profile_intoduce)
        val job1 = view.findViewById<TextView>(R.id.txt_job1)
        val job2 = view.findViewById<TextView>(R.id.txt_job2)
        val job3 = view.findViewById<TextView>(R.id.txt_job3)

        /* 프로필 정보 서버 요청 */
        val profileCall: Call<BaseResponse<ProfileData>> = ApiServiceImpl.service.requestMyProfile(ApiServiceImpl.getToken())
        profileCall.enqueue(
            onSuccess = {
                nickname.text = it.nickname
                followerNum.text = it.followernumber
                followingNum.text = it.followingnumber
                introduce.text = it.introduce
                job1.text = it.task_one
                job2.text = it.task_two
                job3.text = it.task_three
                if(it.front_image == null){
                    // 프로필 이미지가 없을 경우 기본 이미지 적용
                    imgFace.setImageDrawable(getResources().getDrawable(R.drawable.basicprofile_img))
                }else{
                    // 프로필 이미지가 있을 경우 해당 이미지 적용
                    Glide 
                        .with(this)
                        .load(it.front_image)
                        .apply(RequestOptions.circleCropTransform())
                        .into(img_profile_face)
                }
            },
            onFail = {status, message ->  toast(message)
            }
        )

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvInit()
        floatingClick()
        follow()
        laterDevelop()
    }

    private fun follow () {
        val follower : TextView = view!!.findViewById(R.id.txt_profile_follower)
        val followerNum : TextView = view!!.findViewById(R.id.txt_profile_follower_number)

        val following : TextView = view!!.findViewById(R.id.txt_profile_following)
        val followingNum : TextView = view!!.findViewById(R.id.txt_profile_following_number)

        follower.setOnClickListener {
            val intent = Intent(this@MainProfileFragment.context , FollowerListActivity::class.java)
            startActivity(intent)
        }

        followerNum.setOnClickListener {
            val intent = Intent(this@MainProfileFragment.context  , FollowerListActivity::class.java)
            startActivity(intent)
        }

        following.setOnClickListener {
            val intent = Intent(this@MainProfileFragment.context  , FollowingListActivity::class.java)
            startActivity(intent)
        }

        followingNum.setOnClickListener {
            val intent = Intent(this@MainProfileFragment.context , FollowingListActivity::class.java)
            startActivity(intent)
        }
    }
    /* 타임라인 추가 후 새로고침 */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == TIMELINE_ADD_SUCCESS){
            if(resultCode == Activity.RESULT_OK){
                /* 타임라인 조회 새로고침용 서버 요청*/
                val timelineCall: Call<BaseResponse<List<ProfileTimelineData>>> = ApiServiceImpl.service.responseMyTimelineList(ApiServiceImpl.getToken())

                timelineCall.enqueue(
                    onSuccess = {
                        adapter_profile_mainProfile.data = it
                        adapter_profile_mainProfile.notifyDataSetChanged()
                    },
                    onFail = {status, message -> toast(message)
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

        /** 자신의 타임라인 조회 서버 통신 */
        val timelineCall: Call<BaseResponse<List<ProfileTimelineData>>> = ApiServiceImpl.service.responseMyTimelineList(ApiServiceImpl.getToken())

        timelineCall.enqueue(
            onSuccess = {
                adapter_profile_mainProfile.data = it
                adapter_profile_mainProfile.notifyDataSetChanged()
            },
            onFail = {status, message ->
                // 자신의 타임라인이 하나도 존재하지 않을 때 "타임라인이 비어있습니다." 텍스트 띄우기
                if(message == "존재하지 않는 타임라인 입니다."){
                    txt_profile_black.visibility = View.VISIBLE
                }
            }
        )


    }


    fun floatingClick(){
        img_profile_floating.setOnClickListener{
            // 타임라인 추가 도중 뒤로가기 눌렀을 때 count 값 초기화
            SelectHelper.categoryCountInit()

            val intent = Intent(context, TimelineAddActivity::class.java)
            startActivityForResult(intent, TIMELINE_ADD_SUCCESS)
        }
    }

    /* 기능 구현 부족한 부분들 토스트 처리 */
    fun laterDevelop(){
//        txt_profile_following.setOnClickListener {
//            Toast.makeText(context, "팔로우, 팔로잉 기능을 준비중입니다. 조금만 기다려주세요.", Toast.LENGTH_SHORT).show()
//        }
        img_profile_massage.setOnClickListener {
            //TODO! isme 판단해서 나일경우 목록, 상대방일경우 전송 페이지 띄우기
            startActivity(Intent(this@MainProfileFragment.context, MessageActivity::class.java))
        }
    }
}