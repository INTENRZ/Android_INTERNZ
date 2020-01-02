package com.example.internz.feature.homestory

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.home.TodayStoryData
import com.example.internz.ui.story.StoryHelper
import com.example.internz.ui.story.detailstory.DetailStoryActivity


class HomestoryViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    val photo : ImageView = view.findViewById(R.id.imgHomeStoryImg)
    val desc : TextView = view.findViewById(R.id.txtHomeStoryDesc)

    fun bind(data : TodayStoryData)
    {
        //photo.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, data.img, null))
        desc.text = data.title
//        StoryHelper.setStoryIndex(data.storyIdx)
//        itemView.setOnClickListener{
//            val intent = Intent(itemView.context, DetailStoryActivity::class.java)
//            intent.putExtra("storyIdx", data.storyIdx)
//            itemView.context.startActivity(intent)
//        }

    }
}