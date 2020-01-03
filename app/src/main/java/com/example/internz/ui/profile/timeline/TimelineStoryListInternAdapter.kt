package com.example.internz.ui.profile.timeline

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.TimelineStoryListInternData
import com.example.internz.ui.story.StoryHelper
import com.example.internz.ui.story.detailstory.DetailStoryActivity

class TimelineStoryListInternAdapter (private val context : Context) : RecyclerView.Adapter<TimelineStoryListInternViewHolder>() {

    var data = listOf<TimelineStoryListInternData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineStoryListInternViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_timeline_story_list_intern, parent, false)
        return TimelineStoryListInternViewHolder(view)

    }

    override fun onBindViewHolder(holder : TimelineStoryListInternViewHolder, position: Int) {
        holder.bind(data[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailStoryActivity::class.java)
            StoryHelper.setStoryIndex(data[position].storyIdx.toString())
            StoryHelper.setUserIndex(data[position].userIdx.toString())
            Log.d("chohee_setUserIdx", StoryHelper.getUserIndex())
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}