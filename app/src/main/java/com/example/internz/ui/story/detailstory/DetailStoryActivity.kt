package com.example.internz.ui.story.detailstory


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.feature.comment.CommentActivity
import com.example.internz.ui.profile.main.OtherProfileActivity
import com.example.internz.ui.story.StoryHelper
import kotlinx.android.synthetic.main.activity_detail_story.*
import kotlinx.android.synthetic.main.activity_story_add.*


class DetailStoryActivity : AppCompatActivity() {
    private val REQUEST_CODE = 100
    private var userIdx: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_story)

        Log.e("TAG", "storyIndex : ${StoryHelper.getStoryIndex()}")
        detailStoryFunction()
    }

    private fun detailStoryFunction() {
        //통신
        requestBroadcast()

        //스크랩 버튼
        imgDetailScrap?.setOnClickListener {
            //TODO! selecter 지정 필요
            //TODO! 서버 통신 필요
        }

        //댓글 아이콘 click event
        imgCommentIcon?.setOnClickListener{
            startActivityForResult(Intent(this, CommentActivity::class.java), REQUEST_CODE)
        }

        //댓글 텍스트 click listener
        txtCommentCount?.setOnClickListener {
            startActivityForResult(Intent(this, CommentActivity::class.java), REQUEST_CODE)
        }

        //팔로우 버튼
        imgFollow?.setOnClickListener {
            //TODO! 팔로우
        }

        //뒤로 가기 click listener
        storyBackImg?.setOnClickListener {
            this.finish()
        }

        txt_detailStory_nickname.setOnClickListener {
            val intent = Intent(this, OtherProfileActivity::class.java)

//            intent.putExtra("userIdx", userIdx)
            startActivity(intent)
        }

        profileImg.setOnClickListener {
            val intent = Intent(this, OtherProfileActivity::class.java)
//            intent.putExtra("userIdx", userIdx)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            REQUEST_CODE -> {
                when(resultCode) {
                    Activity.RESULT_OK -> {
                        requestBroadcast()
                    }
                }
            }
        }
    }

    private fun requestBroadcast() {
        val call = ApiServiceImpl.service.requestDetailStory(
            ApiServiceImpl.getToken(),
            StoryHelper.getStoryIndex()
        )

        Log.e("TAG", "storyIndex : ${StoryHelper.getStoryIndex()} ")

        call.enqueue(
            onSuccess = {
                val data = it.get(0)
                //upper layout
                txtDetailTitle.text = data.title //제목
                txtDetailNick.text = data.nickname //닉네임
                txtDetailDate.text = data.date.replace("-",".") //날짜
                txt_detailStory_nickname.text = data.nickname
                txt_detailStory_introduce.text = data.introduce
                StoryHelper.setUserIndex(data.userIndex.toString())
                //userIdx = data.userIndex
                Log.d("chohee요기", StoryHelper.getUserIndex())

                //middle layout
                txtMain.text = data.contents //내용
                txtCommentCount.text = data.commentCount.toString() //댓글 개수

                //below layout
                Glide //사용자 이미지 프로필
                    .with(this)
                    .load(data.profile)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profileImg)

                Log.e("TAG", "DetailStoryActivity : onSuccess 메서드 실행됨")
            },
            onFail = {
                    status, message -> Log.e("TAG", "DetailStoryActivity : onFail 메서드 실행됨")
            }
        )
    }


}

