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
import com.example.internz.ui.story.StoryHelper
import kotlinx.android.synthetic.main.activity_detail_story.*


class DetailStoryActivity : AppCompatActivity() {
    private val REQUEST_CODE = 100

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

        //댓글(comment) click listener
        constDetail?.setOnClickListener {
//            startActivity(Intent(this, CommentActivity::class.java))
            startActivityForResult(Intent(this, CommentActivity::class.java), REQUEST_CODE)
        }

        //팔로우 버튼
        imgDetailFollow?.setOnClickListener {
            //TODO! 팔로우
        }

        //뒤로 가기 click listener
        storyBackImg?.setOnClickListener {
            this.finish()
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

        call.enqueue(
            onSuccess = {
                val data = it.get(0)
                //upper layout
                txtDetailTitle.text = data.title //제목
                txtDetailNick1.text = data.nickname //닉네임
                txtDetailDate.text = data.date //날짜

                //middle layout
                txtDetailContents.text = data.contents //내용
                txtDetailCommentCtn.text = data.commentCount.toString() //댓글 개수

                //below layout
                Glide //사용자 이미지 프로필
                    .with(this)
                    .load(data.profile)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imgDetailProfile)
                txtDetailNick.text = data.nickname
                txtDetailIntroduce.text = data.introduce

                Log.e("TAG", "DetailStoryActivity : onSuccess 메서드 실행됨")
            },
            onFail = {
                    status, message -> Log.e("TAG", "DetailStoryActivity : onFail 메서드 실행됨")
            }
        )
    }
}

