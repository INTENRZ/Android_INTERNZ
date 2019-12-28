package com.example.internz.ui.profile

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.TimelineStoryListData

class TimelineStoryListViewHolder (view : View) : RecyclerView.ViewHolder(view) {

    val desc : TextView = view.findViewById(R.id.txtTimelineStoryListDesc)
    val date : TextView = view.findViewById(R.id.txtTimelineStoryListdate)
    val img : ImageView = view.findViewById(R.id.imgTimelineStoryListImg)

    fun bind(data : TimelineStoryListData)
    {

        desc.text = data.desc
        date.text = data.date
        img.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, data.img, null))

    }
}