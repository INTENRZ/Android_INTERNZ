package com.example.internz.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.timeline.TimelineCategoryData


class TimelineCategoryAdapter(private val context: Context) : RecyclerView.Adapter<TimelineCategoryViewHolder>(){

    var data = listOf<TimelineCategoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineCategoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_filterbox_item, parent, false)
        return TimelineCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimelineCategoryViewHolder, position: Int) {
        holder.bind(data[position])
        holder.changeColor()

    }

    override fun getItemCount(): Int {
        return data.size
    }
}