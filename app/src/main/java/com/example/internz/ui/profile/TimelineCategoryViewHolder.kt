package com.example.internz.ui.profile

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.timeline.TimelineCategoryData

class TimelineCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val category: TextView = itemView.findViewById(R.id.txt_category)

    fun bind(data: TimelineCategoryData){
        category.text = data.category
    }
}