package com.example.internz.ui.profile.timeline



import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.TimelineStoryListExternalData

class TimelineStoryListExternalAdapter(private val context : Context) : RecyclerView.Adapter<TimelineStoryListExternalViewHolder>() {

    var data = listOf<TimelineStoryListExternalData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineStoryListExternalViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_timeline_story_list_external, parent, false)
        return TimelineStoryListExternalViewHolder(view)

    }

    override fun onBindViewHolder(holder : TimelineStoryListExternalViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}