package com.example.internz.ui.profile.timeline

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.TimelineStoryListInternData
import com.example.internz.ui.story.StoryHelper

class TimelineStoryListInternAdapter (private val context : Context) : RecyclerView.Adapter<TimelineStoryListInternViewHolder>() {

    var data = listOf<TimelineStoryListInternData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineStoryListInternViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_timeline_story_list_intern, parent, false)
        return TimelineStoryListInternViewHolder(view)

    }

    override fun onBindViewHolder(holder : TimelineStoryListInternViewHolder, position: Int) {
        Log.d("chohee", itemCount.toString())
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}