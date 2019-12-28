package com.example.internz.ui.profile.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.ProfileTimelineData

class MainProfileAdapter(private val context : Context) : RecyclerView.Adapter<MainProfileVeiwHolder>() {

    var data = listOf<ProfileTimelineData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainProfileVeiwHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.rv_profile_timeline_item, parent, false)
        return MainProfileVeiwHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainProfileVeiwHolder, position: Int) {
        holder.bind(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }
}