package com.example.internz.feature.filter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.filter.FilterRequestData

class FilterAdapter(private val context : Context) : RecyclerView.Adapter<FilterViewHolder>() {
    var data = listOf<FilterRequestData>()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_filter_item, parent, false)
        return FilterViewHolder(view)
    }
}