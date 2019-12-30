package com.example.internz.ui.story.detailstory


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.internz.R
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
        //댓글(comment)로 이동 click listener
        imgDetailComment?.setOnClickListener {
            startActivity(Intent(this, CommentActivity::class.java))
        }

        //뒤로 가기 click listener
        storyBackImg?.setOnClickListener {
            this.finish()
        }
    }
}

