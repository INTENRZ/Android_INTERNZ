package com.example.internz.feature.followerlist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.follow.FollowerData

class FollowerListViewHolder (view : View) : RecyclerView.ViewHolder(view) {



        private val name : TextView = view.findViewById(R.id.txtFollowerNick)
        private val desc : TextView = view.findViewById(R.id.txtFollowerContents)
        private val profileImg : ImageView = view.findViewById(R.id.imgFollowerUser)


        fun bind(data : FollowerData) {
            name.text = data.name
            desc.text = data.desc
            profileImg.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, data.profileImg, null))

        }
    }