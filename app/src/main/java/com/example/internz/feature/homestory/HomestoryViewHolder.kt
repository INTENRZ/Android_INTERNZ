package com.example.internz.feature.homestory

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.home.StoryData


class HomestoryViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    val photo : ImageView = view.findViewById(R.id.imgHomeStoryImg)
    val desc : TextView = view.findViewById(R.id.txtHomeStoryDesc)

    fun bind(data : StoryData)
    {
        photo.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, data.img, null))
        desc.text = data.desc
    }
}