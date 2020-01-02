package com.example.internz.ui.profile.main

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.ProfileTimelineData
import com.example.internz.ui.profile.timeline.TimelineStoryHelper
import com.example.internz.ui.profile.timeline.TimelineStoryListInternActivity


class MainProfileVeiwHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val timelineTitle : TextView = itemView.findViewById(R.id.txt_profile_timeline_title)
    val timelinePeriod : TextView = itemView.findViewById(R.id.txt_profile_timeline_period)
    val timelineCategory : TextView = itemView.findViewById(R.id.txt_profile_timeline_category)

    fun bind(data: ProfileTimelineData){
        timelineTitle.text = data.title
        timelinePeriod.text = data.start_date + " - " + data.end_date
        timelineCategory.text = data.category

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, TimelineStoryListInternActivity::class.java)
            TimelineStoryHelper.timelineIdx = data.timelineIdx
            TimelineStoryHelper.category = data.category
            TimelineStoryHelper.title = data.title
            TimelineStoryHelper.period = data.start_date + " - " + data.end_date
            Log.d("chohee타임라인스토리인덱스", data.timelineIdx.toString())
//            intent.putExtra("timelineIdx", data.timelineIdx.toString())
            itemView.context.startActivity(intent)

        }
    }
}