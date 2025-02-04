package com.example.internz.feature.homestory

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.home.TodayStoryData
import com.example.internz.ui.profile.main.OtherProfileActivity
import com.example.internz.ui.story.StoryHelper
import com.example.internz.ui.story.detailstory.DetailStoryActivity

class HomestoryAdapter(private val context : Context) : RecyclerView.Adapter<HomestoryViewHolder>() {

//    var data = listOf<StoryData>()
    var data = listOf<TodayStoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomestoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_home_story_item, parent, false)
        return HomestoryViewHolder(view)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: HomestoryViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailStoryActivity::class.java)
//            intent.putExtra("storyIdx", data.storyIdx.toString())
            StoryHelper.setStoryIndex(data[position].storyIdx.toString())
            holder.itemView.context.startActivity(intent)
        }
    }
}