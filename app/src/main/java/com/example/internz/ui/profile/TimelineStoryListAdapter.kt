package com.example.internz.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.TimelineStoryListData

class TimelineStoryListAdapter (private val context : Context) : RecyclerView.Adapter<TimelineStoryListViewHolder>() {

    var data = listOf<TimelineStoryListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineStoryListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_timeline_storylist, parent, false)
        return TimelineStoryListViewHolder(view)

    }

    override fun onBindViewHolder(holder: TimelineStoryListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}