package com.example.internz.feature.homestory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.home.StoryData

class HomestoryAdapter(private val context : Context) : RecyclerView.Adapter<HomestoryViewHolder>() {

    var data = listOf<StoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomestoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_home_story_item, parent, false)
        return HomestoryViewHolder(view)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: HomestoryViewHolder, position: Int) {
        holder.bind(data[position])
    }
}