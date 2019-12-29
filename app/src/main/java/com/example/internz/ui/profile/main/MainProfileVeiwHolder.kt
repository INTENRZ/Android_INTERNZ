package com.example.internz.ui.profile.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.ProfileTimelineData


class MainProfileVeiwHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val timelineBar: ImageView = itemView.findViewById(R.id.img_profile_timeline_bar)
    val timelineTitle : TextView = itemView.findViewById(R.id.txt_profile_timeline_title)
    val timelinePeriod : TextView = itemView.findViewById(R.id.txt_profile_timeline_period)

    fun bind(data: ProfileTimelineData){
        timelineTitle.text = data.timelineTitle
        timelinePeriod.text = data.timelinePeriodStart + " - " + data.timelinePeriodEnd
    }


}