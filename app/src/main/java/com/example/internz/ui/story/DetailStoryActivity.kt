package com.example.internz.ui.story


import StoryFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.internz.R



class DetailStoryActivity : AppCompatActivity() {

    //val backicon : ImageView = findViewById(R.id.storyBackImg)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_story)

//        backicon.setOnClickListener {
//            val intent = Intent(this , StoryFragment::class.java)
//            startActivity(intent)
//        }

    }
}

