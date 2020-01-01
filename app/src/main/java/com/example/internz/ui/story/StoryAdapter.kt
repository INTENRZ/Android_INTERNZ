package com.example.internz.ui.story

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.home.StoryData
import com.example.internz.data.story.StoryCategoryResponseData
import com.example.internz.ui.story.detailstory.DetailStoryActivity

class StoryAdapter(private val context : Context) : RecyclerView.Adapter<StoryViewHolder>() {
    var data = listOf<StoryCategoryResponseData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_story_item, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(data[position])

        //스토리 click event
        holder.itemView?.setOnClickListener {
            StoryHelper.setStoryIndex(data[position].storyIdx.toString())
            Log.e("TAG", "${data[position].storyIdx.toString()}")

            val intent = Intent(holder.itemView.context, DetailStoryActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}