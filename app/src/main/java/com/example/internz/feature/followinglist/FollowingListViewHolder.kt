package com.example.internz.feature.followinglist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.follow.FollowingData

class FollowingListViewHolder (view : View) : RecyclerView.ViewHolder(view) {


    private val name : TextView = view.findViewById(R.id.txtFollowingNick)
    private val desc : TextView = view.findViewById(R.id.txtFollowingContents)
    private val profileImg : ImageView = view.findViewById(R.id.imgFollowingUser)


    fun bind(data : FollowingData) {
        name.text = data.name
        desc.text = data.desc
        profileImg.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, data.profileImg, null))

    }
}