package com.example.internz.feature.followerlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.follow.FollowerResponseData


class FollowerListAdapter (private val context : Context) : RecyclerView.Adapter<FollowerListViewHolder>() {

    var data = listOf<FollowerResponseData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_follower_item, parent, false)
        return FollowerListViewHolder(view)
    }

    override fun getItemCount(): Int {

       return data.size
    }

    override fun onBindViewHolder(holder: FollowerListViewHolder, position: Int) {

        holder.bind(data[position])

    }
}
