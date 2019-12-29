package com.example.internz.feature.followinglist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.follow.FollowingData


class FollowingListAdapter (private val context : Context) : RecyclerView.Adapter<FollowingListViewHolder>() {

    var data = listOf<FollowingData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_follower_item, parent, false)
        return FollowingListViewHolder(view)
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: FollowingListViewHolder, position: Int) {

        holder.bind(data[position])
        holder.itemView.setOnClickListener {

        }
    }
}
