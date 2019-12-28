package com.example.internz.ui.profile.timeline

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.TimelineStoryListInternData

class TimelineStoryListInternViewHolder (view : View) : RecyclerView.ViewHolder(view) {

    val desc : TextView = view.findViewById(R.id.txtTimelineStoryListInternDesc)
    val date : TextView = view.findViewById(R.id.txtTimelineStoryListInternDate)
    val img : ImageView = view.findViewById(R.id.imgTimelineStoryListInternImg)

    fun bind(internData : TimelineStoryListInternData)
    {

        desc.text = internData.desc
        date.text = internData.date
        img.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, internData.img, null))

    }
}