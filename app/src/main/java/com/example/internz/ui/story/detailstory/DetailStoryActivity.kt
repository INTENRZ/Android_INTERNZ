package com.example.internz.ui.story.detailstory


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
import kotlinx.android.synthetic.main.activity_detail_story.*


class DetailStoryActivity : AppCompatActivity() {
    //val backicon : ImageView = findViewById(R.id.storyBackImg)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_story)

        detailStoryFunction()
    }

    private fun detailStoryFunction() {
        Log.e("TAG", "DetailStoryActivity : storyIdx : ${intent.getStringExtra("storyIndex")}")

        //통신
        val call = ApiServiceImpl.service.requestDetailStory(
            ApiServiceImpl.getToken(),
//            intent.getStringExtra("storyIndex")
        "15"
        )

        call.enqueue(
            onSuccess = {
                //upper layout
                txtDetailTitle.text = it.title //제목
                txtDetailNick1.text = it.nickname //닉네임
                txtDetailDate.text = it.date //날짜

                //middle layout
                txtDetailContents.text = it.contents //내용
                txtDetailCommentCtn.text = it.commentCount.toString() //댓글 개수

                //below layout
                Glide //사용자 이미지 프로필
                    .with(this)
                    .load(it.profile)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imgDetailProfile)
                txtDetailNick.text = it.nickname
                txtDetailIntroduce.text = it.introduce

                Log.e("TAG", "DetailStoryActivity : onSuccess 메서드 실행됨")
            },
            onFail = {
                status, message -> Log.e("TAG", "DetailStoryActivity : onFail 메서드 실행됨")
            }
        )

        //스크랩 버튼
        imgDetailScrap?.setOnClickListener {
            //TODO! selecter 지정 필요
            //TODO! 서버 통신 필요
        }

        //댓글(comment)로 이동 click listener
        imgDetailComment?.setOnClickListener {
            startActivity(
                Intent(this, CommentActivity::class.java)
                    .putExtra("storyIndex", intent.getStringExtra("storyIndex")))
        }

        //댓글(comment) click listener
        constDetail?.setOnClickListener {
            startActivity(Intent(this, CommentActivity::class.java))
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
}

