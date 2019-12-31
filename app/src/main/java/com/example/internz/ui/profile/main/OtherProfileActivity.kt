package com.example.internz.ui.profile.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.BaseResponse
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.UserIdxRequestData
import com.example.internz.data.profile.ProfileData
import com.example.internz.data.profile.ProfileTimelineData
import kotlinx.android.synthetic.main.activity_other_profile.*
import retrofit2.Call

class OtherProfileActivity : AppCompatActivity() {

    private lateinit var rv_otherTimeline: RecyclerView
    private lateinit var adapter_otherTimeline: MainProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_profile)


        var userIdx = intent.getStringExtra("userIdx")
        /* 프로필 정보 뷰 객체 초기화 */
        val nickname = findViewById<TextView>(R.id.txt_otherProfile_name)
        val imgFace = findViewById<ImageView>(R.id.img_otherProfile_face)
        val followerNum = findViewById<TextView>(R.id.txt_otherProfile_follower_number)
        val followingNum = findViewById<TextView>(R.id.txt_otherProfile_following_number)
        val introduce = findViewById<TextView>(R.id.txt_otherProfile_intoduce)
        val job1 = findViewById<TextView>(R.id.txt_job1)
        val job2 = findViewById<TextView>(R.id.txt_job2)
        val job3 = findViewById<TextView>(R.id.txt_job3)

        val call: Call<BaseResponse<ProfileData>> = ApiServiceImpl.service.requestOtherProfile(ApiServiceImpl.getToken(), UserIdxRequestData( userIdx.toInt()))
        call.enqueue(
            onSuccess = {
                Log.d("chohee", it.toString())
                nickname.text = it.nickname
                followerNum.text = it.followernumber
                followingNum.text = it.followingnumber
                introduce.text = it.introduce
                job1.text = it.task_one
                job2.text = it.task_two
                job3.text = it.task_three
                if(it.front_image == "undefined"){
                    // 프로필 설정 이미지가 없을 경우 기본 이미지 지정
                    imgFace.setImageDrawable(getResources().getDrawable(R.drawable.basicprofile_img))
                }else{

                }
            },
            onFail = {status, message ->  toast(message)
            }
        )


        backBtn()
        rvSetting()
    }

    /* 타임라인 리사이클러뷰 세팅 */
    fun rvSetting(){
        rv_otherTimeline = findViewById(R.id.rv_otherProfile_timeline)
        adapter_otherTimeline = MainProfileAdapter(this)
        rv_otherTimeline.adapter = adapter_otherTimeline
        rv_otherTimeline.layoutManager = LinearLayoutManager(this)


        //val call: Call<>
        //adapter_otherTimeline.notifyItemChanged()
    }

    fun backBtn(){
        img_backarrow.setOnClickListener {
            finish()
        }
    }

}
