package com.example.internz.feature.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.calendar.CalendarData
import com.example.internz.data.calendar.CalenderResponseData

class CalendarAdapter(private val context : Context) : RecyclerView.Adapter<CalendarViewHolder>() {
    var data = listOf<CalenderResponseData>()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_calendar_item, parent, false)
        return CalendarViewHolder(view)
    }
}