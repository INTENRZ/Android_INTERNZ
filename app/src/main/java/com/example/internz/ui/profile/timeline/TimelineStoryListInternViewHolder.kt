package com.example.internz.ui.profile.timeline

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.TimelineStoryListInternData

class TimelineStoryListInternViewHolder (view : View) : RecyclerView.ViewHolder(view) {

    val title : TextView = view.findViewById(R.id.txtTimelineStoryListInternTitle)
    val date : TextView = view.findViewById(R.id.txtTimelineStoryListInternDate)
    val img : ImageView = view.findViewById(R.id.imgTimelineStoryListInternImg)

    fun bind(internData : TimelineStoryListInternData)
    {

        title.text = internData.title
        date.text = internData.updated_date
        //img.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, internData.img, null))

    }
}