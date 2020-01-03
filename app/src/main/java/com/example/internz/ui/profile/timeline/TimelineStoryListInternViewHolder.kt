package com.example.internz.ui.profile.timeline

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.TimelineStoryListInternData
import com.example.internz.ui.story.StoryHelper
import com.example.internz.ui.story.detailstory.DetailStoryActivity

class TimelineStoryListInternViewHolder (view : View) : RecyclerView.ViewHolder(view) {

    val title : TextView = view.findViewById(R.id.txtTimelineStoryListInternTitle)
    val date : TextView = view.findViewById(R.id.txtTimelineStoryListInternDate)
    val img : ImageView = view.findViewById(R.id.imgTimelineStoryListInternImg)
    val bar : View = view.findViewById(R.id.bar)

    fun bind(internData : TimelineStoryListInternData)
    {

        title.text = internData.title
        date.text = internData.created_date
//        itemView.setOnClickListener {
//            val intent = Intent(itemView.context, DetailStoryActivity::class.java)
//            StoryHelper.setStoryIndex(internData.storyIdx.toString())
//            itemView.context.startActivity(intent)
//        }

    }
}