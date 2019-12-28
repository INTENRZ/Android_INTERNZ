package com.example.internz.ui.profile.timeline

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.TimelineStoryListExternalData

class TimelineStoryListExternalViewHolder (view : View) : RecyclerView.ViewHolder(view) {

    val desc : TextView = view.findViewById(R.id.txtTimelineStoryListExternalDesc)
    val date : TextView = view.findViewById(R.id.txtTimelineStoryListExternalDate)
    val img : ImageView = view.findViewById(R.id.imgTimelineStoryListExternalImg)

    fun bind(data : TimelineStoryListExternalData)
    {

        desc.text = data.desc
        date.text = data.date
        img.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, data.img, null))

    }
}